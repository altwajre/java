# Performing Common ETL Functions in Pig

*cloudera*

- `Query Editors`, `Pig`

**Filter**

- enter following script, and click `Play` button

```
A = load 'Customers.csv' using PigStorage (',') as (Acct_no,Acct_name,Address,City,State,ZIP);
B = Filter A by State matches 'CA';
store B into 'Filtered';
```

- wait for it to be completed

- `File Browser`, `Filtered/part-m-00000`

```
1000567	Jupiter Networks	203 Packet Road	Sunnyvale	CA	94085
1000386	Andicle	506 Database Drive	Redwood City	CA	94061
1000336	Banana Computer	607 Infinity Road	Cupertino	CA	94024
```

**Union**

- enter following script, and click `Play` button

```
A = load 'Transactions.csv' using PigStorage (',');
B = load 'MoreTransactions.csv' using PigStorage (',');
C = union A,B;
D = group C by 1;
E = foreach D generate flatten (C);
store E into 'Results';
```

- wait for it to be completed

- `File Browser', `Results/part-r-00000`

```
1000245	B444-400	550.00	80
1000998	A56-7W	700.00	67
1000356	SFP-10G	22.00	67
1000386	SFP-10G	22.00	66
1000567	SFP-10G	22.00	65
1000326	DD-214	667.00	64
1000386	C2500-40G	9999.00	57
1000356	2010TYW-MC	88.00	55
1000998	2500-M	285.00	51
1000890	MITY-2014	5.00	50
1000336	2010TYW-MC	88.00	556
1000890	2010TYW-MC	88.00	556
1000245	MITY-2014	5.00	540
1000998	2001-ASO	990.00	200
1000567	B444-400	550.00	177
1000356	MITY-2014	5.00	157
1000245	2010TYW-MC	88.00	105
1000356	2001-ASO	990.00	100
1000446	A56-7W	700.00	90
1000326	SFP-10G	22.00	80
1000356	DD-214	667.00	44
1000998	MITY-2014	5.00	34
1000386	2500-M	285.00	34
1000386	B444-400	550.00	25
1000386	DD-214	667.00	24
1000356	2500-M	285.00	22
1000245	DD-214	667.00	22
1000567	A56-7W	700.00	20
1000567	2500-M	285.00	15
1000245	2001-ASO	990.00	15
1000356	A56-7W	700.00	12
1000890	2001-ASO	990.00	9
1000567	DD-214	667.00	9
1000245	SFP-10G	22.00	9
1000269	C2500-40G	9999.00	8
1000245	C2500-40G	9999.00	6
1000356	B444-400	550.00	5
1000245	2500-M	285.00	5
1000386	1000GXR	22350.00	4
1000890	1000GXR	22350.00	2
1000567	C2500-40G	9999.00	2
1000356	C2500-40G	9999.00	2
1000245	A56-7W	700.00	2
1000356	1000GXR	22350.00	1
1000245	1000GXR	22350.00	1
```

**Join**

- enter following script, and click `Play` button

```
A = load 'Customers.csv' using PigStorage (',') as (AcctNo,AcctName,Address,City,State,ZIP);
B = load 'Results/part-r-00000' as (AcctNo,PartNo,Price,Qty);
C = join A by (AcctNo), B by (AcctNo);
store C into 'Joined';
```

- wait for it to be completed

- `File Browser', `Joined/part-r-00000`

```
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	B444-400	550.00	80
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	A56-7W	700.00	2
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	2500-M	285.00	5
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	C2500-40G	9999.00	6
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	SFP-10G	22.00	9
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	2001-ASO	990.00	15
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	DD-214	667.00	22
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	1000GXR	22350.00	1
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	2010TYW-MC	88.00	105
1000245	Toms Produce	100 Toms Lane	Charlotte	NC	28201	1000245	MITY-2014	5.00	540
1000269	Dash Cellular	900 Dash Drive	Overland Park	KS	66204	1000269	C2500-40G	9999.00	8
1000326	Jimsung	809 Electronic Lane	Ridgefield Park	NJ	07660	1000326	DD-214	667.00	64
1000326	Jimsung	809 Electronic Lane	Ridgefield Park	NJ	07660	1000326	SFP-10G	22.00	80
1000336	Banana Computer	607 Infinity Road	Cupertino	CA	94024	1000336	2010TYW-MC	88.00	556
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	2001-ASO	990.00	100
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	A56-7W	700.00	12
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	2500-M	285.00	22
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	SFP-10G	22.00	67
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	DD-214	667.00	44
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	MITY-2014	5.00	157
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	1000GXR	22350.00	1
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	C2500-40G	9999.00	2
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	2010TYW-MC	88.00	55
1000356	Contruction Depot	102 Peachtree Road	Atlanta	GA	30350	1000356	B444-400	550.00	5
1000386	Andicle	506 Database Drive	Redwood City	CA	94061	1000386	SFP-10G	22.00	66
1000386	Andicle	506 Database Drive	Redwood City	CA	94061	1000386	2500-M	285.00	34
1000386	Andicle	506 Database Drive	Redwood City	CA	94061	1000386	1000GXR	22350.00	4
1000386	Andicle	506 Database Drive	Redwood City	CA	94061	1000386	C2500-40G	9999.00	57
1000386	Andicle	506 Database Drive	Redwood City	CA	94061	1000386	B444-400	550.00	25
1000386	Andicle	506 Database Drive	Redwood City	CA	94061	1000386	DD-214	667.00	24
1000446	Yummy Restaurants	405 Cardinal Avenue	Louisville	KY	40203	1000446	A56-7W	700.00	90
1000567	Jupiter Networks	203 Packet Road	Sunnyvale	CA	94085	1000567	C2500-40G	9999.00	2
1000567	Jupiter Networks	203 Packet Road	Sunnyvale	CA	94085	1000567	SFP-10G	22.00	65
1000567	Jupiter Networks	203 Packet Road	Sunnyvale	CA	94085	1000567	B444-400	550.00	177
1000567	Jupiter Networks	203 Packet Road	Sunnyvale	CA	94085	1000567	DD-214	667.00	9
1000567	Jupiter Networks	203 Packet Road	Sunnyvale	CA	94085	1000567	2500-M	285.00	15
1000567	Jupiter Networks	203 Packet Road	Sunnyvale	CA	94085	1000567	A56-7W	700.00	20
1000890	Scottish Petroleum	304 Scottish Blvd	Houston	TX	77079	1000890	2001-ASO	990.00	9
1000890	Scottish Petroleum	304 Scottish Blvd	Houston	TX	77079	1000890	1000GXR	22350.00	2
1000890	Scottish Petroleum	304 Scottish Blvd	Houston	TX	77079	1000890	MITY-2014	5.00	50
1000890	Scottish Petroleum	304 Scottish Blvd	Houston	TX	77079	1000890	2010TYW-MC	88.00	556
1000998	McDougals	708 Burgerton Avenue	Oak Brook	IL	60523	1000998	MITY-2014	5.00	34
1000998	McDougals	708 Burgerton Avenue	Oak Brook	IL	60523	1000998	2001-ASO	990.00	200
1000998	McDougals	708 Burgerton Avenue	Oak Brook	IL	60523	1000998	2500-M	285.00	51
1000998	McDougals	708 Burgerton Avenue	Oak Brook	IL	60523	1000998	A56-7W	700.00	67
```
