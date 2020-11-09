package com.sbs.practice.easytextboard.controller;

import java.util.Scanner;

import com.sbs.practice.easytextboard.container.Container;
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

	}

	private void join(String cmd) {
		System.out.println("== ȸ������ ==");
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

			boolean isValidAccountName = userService.isValidAccountName(accountName);
			if (isValidAccountName == false) {
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
