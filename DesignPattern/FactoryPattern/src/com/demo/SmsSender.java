package com.demo;

import com.demo.interfaces.Sender;
/**
 * 创建实现类
 * @author Hugs
 * @2014-11-14
 */
public class SmsSender implements Sender {

	@Override
	public void send() {
		//...
		System.out.println("this is sms sender!");  
	}

}
