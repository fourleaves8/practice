package com.sbs.practice.easytextboard;

import java.util.Scanner;

import com.sbs.practice.easytextboard.container.Container;
import com.sbs.practice.easytextboard.controller.UserController;

public class App {

	private UserController userController = new UserController();

	public void run() {
		Scanner sc = Container.sc;

		while (true) {
			System.out.print("��ɾ�) ");
			String cmd = sc.nextLine();

			if (cmd.equals("system exit")) {
				System.out.println("���α׷��� �����մϴ�.");
				break;
			} else if (cmd.startsWith("user ")) {
				userController.doCmd(cmd);
			}

		}

	}

}
