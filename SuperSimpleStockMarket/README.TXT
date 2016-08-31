README for Assignment - Super Simple Stock Market

Davi Baccan
31-08-2016

1) Initial considerations

The system consists of several classes (please see the file ClassDiagram.png). It was developed using Java 1.8.

The Stock class is an abstraction for all kinds of stocks. It includes all the variables and methods that are generic for all kinds of stocks. 
The Stock class also encapsulates the trades (represented by the Trade class) and has methods for calculating the volume weighted stock price and P/E Ratio.

CommonStock and PreferredStock are types of stocks so we implement them as subclasses of the Stock class. 
Due to the fact that the computation of the dividend yield depends on the type of stock, the actual implementation is done in each subclass.

Last but not least, the "stock market" in itself is represented by the GbceExchange class.

2) How to use the system

All stocks, both CommonStock and PreferredStock, should be added to the GbceExchange class so that we can track them and calculate the GBCE All Share Index. 
The list of trades belongs to each stock so that a Trade should be recorded in each Stock class by adding the required information, with special attention to current time 
in which the trade took place.

3) How to test the system

Please see Testing class in which we present some JUnit examples.

4) Assumptions

I assume that:
- the traded quantity is an integer number
- the calculation of the GBCE All Share Index takes into account the past five minutes 
- the buy or sell indicator was included for future use, though it has not been actually used in this system
- stocks have last dividend and fixed dividend fields

