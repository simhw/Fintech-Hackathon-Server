package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reits {

	String type;	// 종목명 
	String price;	// 현재가 
	String pcr;		// 등락률 
	String pcp; 	// 등락가 
	String volume;	// 거래량 
	
}
