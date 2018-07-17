package com.androidbook.services.remote;
import  com.androidbook.services.remote.Person;

interface IAidlService
{
    double getQuote(String ticker);
    String get2Quote(String ticker,in Person requester);
}