package com.example.wallet.carrier;

public record UserTransactionRequestCarrier(String SourceUsername , String DestinationUsername , Long amount){
}
