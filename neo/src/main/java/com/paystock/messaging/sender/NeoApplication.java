package com.paystock.messaging.sender;

import com.paystock.messaging.sender.service.SendMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
@RequiredArgsConstructor
public class NeoApplication implements CommandLineRunner {
	private final SendMessageService sendMessageService;
	public static void main(String[] args) {
		SpringApplication.run(NeoApplication.class, args);
	}

	//FIXME : 클래스 분리
	@Override
	public void run(String... args){
		Scanner sc = new Scanner(System.in);
		System.out.print("메시지를 보낼 사용자명을 입력해주세요 : ");
		String userId = sc.nextLine();
		sendMessageService.setUserId(userId);
		while(sc.hasNextLine()){
			sendMessageService.send(sc.nextLine());
		}
		sc.close();
 	}
}
