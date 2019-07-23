# Wallet Application
Please consider 4 fixes and 2 optimizations and 2 assumptions:

1- All Controller can have Pageable findAll methods, like FinancialTransactionController class.  

2- Validation should be check again more thoroughly.
 
3- Security should be added to be production ready.

4- Wallet Service in k8s is added for testing via postman and web browser, when frontend is developed
    this backend app will have just a cluster ip.

Optimizations:

1- model mapper can be changed to mapstructs because of its performance.

2- Jenkinsfile should be added for CICD and DevOps.

And assumptions: 

1- Transaction Id is considered unique per player.

2- Debit is negative sign numbers and credit are positive numbers in transactions.

### Author:
shahrooz.sabet@gmail.com 2019/08/09
