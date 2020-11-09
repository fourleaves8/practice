package com.sbs.practice.easytextboard.controller;

import java.util.Scanner;

import com.sbs.practice.easytextboard.container.Container;
import com.sbs.practice.easytextboard.dto.User;
import com.sbs.practice.easytextboard.service.UserService;

public class UserController {
	private Scanner sc;
	private UserService userService;

	public UserController() {
		sc = Container.sc;
		userService = Container.userService;
	}

	public void doCmd(String cmd) {

		if (cmd.equals("user join")) {
			join(cmd);
		}
		else if (cmd.equals("user login")) {
			login(cmd);
		}

	}

	private void login(String cmd) {
		System.out.println("== ȸ�� �α��� ==");
		String accountName;
		String accountPw;
		int maxFailCount = 3;
		int failCount = 0;
		
		while (true) {
			
			if (failCount >= maxFailCount) {
				System.out.println("�α����� ����մϴ�.");
				return;
			}

			System.out.printf("���̵� : ");
			accountName = sc.nextLine().trim();

			User user = userService.getUserByAccNm(accountName);
			
			if (accountName.length() == 0) {
				System.out.printf("�ùٸ� ���̵� �Է��ϼ���.%n", accountName);
				failCount++;
				continue;
			}
			if (user == null) {
				System.out.printf("%s�� �������� �ʴ� ���̵� �Դϴ�.%n", accountName);
				failCount++;
				continue;
			} 
			failCount = 0;
			break;
		}
		
		
	}

	private void join(String cmd) {
		System.out.println("== ȸ�� ���� ==");
		String accountName;
		String accountPw;
		String name;
		int maxFailCount = 3;
		int failCount = 0;

		while (true) {

			if (failCount >= maxFailCount) {
				System.out.println("ȸ�������� ����մϴ�.");
				return;
			}

			System.out.printf("����Ͻ� ���̵� : ");
			accountName = sc.nextLine().trim();

			boolean isValidAccNm = userService.isValidAccNm(accountName);
			if (isValidAccNm == false) {
				System.out.printf("%s�� �̹� ������� ���̵� �Դϴ�.%n", accountName);
				failCount++;
				continue;
			} else if (accountName.length() == 0) {
				System.out.println("�ùٸ� ���̵� �Է��ϼ���.");
				failCount++;
				continue;
			}
			failCount = 0;
			break;
		}

		while (true) {
			if (failCount >= maxFailCount) {
				System.out.println("ȸ�������� ����մϴ�.");
				return;
			}

			System.out.printf("����Ͻ� ��й�ȣ : ");
			accountPw = sc.nextLine().trim();

			if (accountPw.length() == 0) {
				System.out.println("�ùٸ� ��й�ȣ�� �Է��ϼ���.");
				failCount++;
				continue;
			}
			failCount = 0;
			break;
		}

		while (true) {
			if (failCount >= maxFailCount) {
				System.out.println("ȸ�������� ����մϴ�.");
				return;
			}

			System.out.printf("�̸� : ");
			name = sc.nextLine().trim();

			if (name.length() == 0) {
				System.out.println("�ùٸ� �̸��� �Է��ϼ���.");
				failCount++;
				continue;
			}
			failCount = 0;
			break;
		}

		int userId = userService.join(accountName, accountPw, name);
		System.out.printf("%d�� ȸ���� �����Ǿ����ϴ�.%n", userId);
	}

}
