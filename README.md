# Wallet Application
Please consider 3 fixes and 2 optimizations and 2 assumptions:

1- All Controller can have Pageable findAll methods, like FinancialTransactionController class.  

2- Validation should be check again more thoroughly.
 
3- Security should be added to be production ready.

Optimizations:

1- model mapper can be changed to mapstructs because of its performance.

2- Jenkinsfile should be added for CICD and DevOps.

And assumptions: 

1- Transaction Id is considered unique per player.

2- Debit is negative sign numbers and credit are positive numbers in transactions.

### Author:
shahrooz.sabet@gmail.com 2019/08/09
