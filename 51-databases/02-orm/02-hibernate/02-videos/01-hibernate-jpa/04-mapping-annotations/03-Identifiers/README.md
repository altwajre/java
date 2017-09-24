# Identifiers

1. @GeneratedValue(strategy = GenerationType.IDENTITY) asks database to generate primary key (database schema AI: auto increment)

2. default is @GeneratedValue = @GeneratedValue(strategy = GenerationType.AUTO) will hibernate to pick the strategy for you
