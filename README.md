#Fund Top 10 Holdings API
This project will snoop fund top 10 holdings from yahoo site and expose it as an API. If there is any change in the yahoo response then this project needs to be updated.


This project depends on net.sourceforge.htmlunit library to scrape the information from yahoo site. 

Yahoo URL we scrape the top 10 holdings information: https://finance.yahoo.com/quote/{FUND_TICKER}/holdings?p={FUND_TICKER}
Example URL to get fund ticker - VIGIX :  https://finance.yahoo.com/quote/VIGIX/holdings?p=VIGIX