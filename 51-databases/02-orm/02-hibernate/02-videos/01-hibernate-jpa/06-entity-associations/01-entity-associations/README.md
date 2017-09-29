# Entity Associations

## Types of Entity Associations

> One to One

- One account is associated with one set of credentials

> One to Many

- One account is associated with many transactions

> Many to One

- Many transactions are associated with one account

> Many to Many

- Many users can be associated with many accounts

## Data Model

one to many relationship - one `account` can have multiple `transactions`

describe account; - pk = ACCOUNT_ID
+-------------------+---------------+------+-----+---------+----------------+
| Field             | Type          | Null | Key | Default | Extra          |
+-------------------+---------------+------+-----+---------+----------------+
| ACCOUNT_ID        | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| BANK_ID           | bigint(20)    | YES  | MUL | NULL    |                |
| ACCOUNT_TYPE      | varchar(45)   | YES  | MUL | NULL    |                |
| NAME              | varchar(100)  | YES  |     | NULL    |                |
| INITIAL_BALANCE   | decimal(10,2) | NO   |     | NULL    |                |
| CURRENT_BALANCE   | decimal(10,2) | NO   |     | NULL    |                |
| OPEN_DATE         | date          | NO   |     | NULL    |                |
| CLOSE_DATE        | date          | NO   |     | NULL    |                |
| LAST_UPDATED_BY   | varchar(45)   | NO   |     | NULL    |                |
| LAST_UPDATED_DATE | datetime      | YES  |     | NULL    |                |
| CREATED_BY        | varchar(45)   | YES  |     | NULL    |                |
| CREATED_DATE      | datetime      | YES  |     | NULL    |                |
+-------------------+---------------+------+-----+---------+----------------+

describe transaction;
+-------------------+---------------+------+-----+---------+----------------+
| Field             | Type          | Null | Key | Default | Extra          |
+-------------------+---------------+------+-----+---------+----------------+
| TRANSACTION_ID    | bigint(20)    | NO   | PRI | NULL    | auto_increment |
| ACCOUNT_ID        | bigint(20)    | NO   | MUL | NULL    |                |
| TRANSACTION_TYPE  | varchar(45)   | NO   |     | NULL    |                |
| TITLE             | varchar(100)  | NO   |     | NULL    |                |
| AMOUNT            | decimal(10,2) | NO   |     | NULL    |                |
| INITIAL_BALANCE   | decimal(10,2) | NO   |     | NULL    |                |
| CLOSING_BALANCE   | decimal(10,2) | NO   |     | NULL    |                |
| NOTES             | mediumtext    | YES  |     | NULL    |                |
| LAST_UPDATED_BY   | varchar(45)   | NO   |     | NULL    |                |
| LAST_UPDATED_DATE | datetime      | NO   |     | NULL    |                |
| CREATED_BY        | varchar(45)   | NO   |     | NULL    |                |
| CREATED_DATE      | datetime      | NO   |     | NULL    |                |
+-------------------+---------------+------+-----+---------+----------------+

## Object Model

- Reference from one entity to another

- Mapped to underlying database tables that support the relationship

- Source Object (Transaction)/Target Object (Account)

> Bidirectional

Account - can have multiple `Transaction`

```
@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCOUNT_ID")
	private Long accountId;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="account")
	List<Transaction> transactions = new ArrayList<>();
```

Transaction - has `Account` reference

```
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private Long transactionId;

	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID")
	private Account account;
```



