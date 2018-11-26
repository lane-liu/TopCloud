package com.top.cloud.web;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = -1546116066538575660L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		// ͼƬ���ڴ�ӳ��
		BufferedImage image = new BufferedImage(60, 20,
				BufferedImage.TYPE_INT_RGB);
		// ��û��ʶ���
		Random r = new Random();
		Graphics g = image.getGraphics();
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.fillRect(0, 0, 60, 20);
		g.setColor(new Color(0, 0, 0));
		String number = String.valueOf(r.nextInt(99999));
		request.getSession().setAttribute("number",number);
		System.out.println("�����������֤����:" + number);
		
		g.drawString(number, 5, 15);

		// ��ɸ�����
		g.drawLine(r.nextInt(60), r.nextInt(20), r.nextInt(60), r.nextInt(20));
		g.drawLine(r.nextInt(60), r.nextInt(20), r.nextInt(60), r.nextInt(20));
		g.drawLine(r.nextInt(60), r.nextInt(20), r.nextInt(60), r.nextInt(20));

		// ѹ����jpeg��ʽ
		OutputStream os = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);

		// ��BufferedImage�����е�ͼ����Ϣ�����
		// �򴴽��ö���(encoder)ʱָ������������
		encoder.encode(image);
	}

}
