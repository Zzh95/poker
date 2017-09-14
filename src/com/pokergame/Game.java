package com.pokergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game{
	List<cards> card=new ArrayList<cards>();
	player player1=new player();
	player player2=new player();
	/**
	 * �����˿�����
	 * �������ԣ���ɫ���˿��Ƶ�ֵ
	 *
	 */
	public class cards{
		String number;
		String color;
		public cards(String number,String color){
			this.number=number;
			this.color=color;
		}
	}
	/**
	 * ���������
	 * �������ԣ�������ID�ͻ�õ��Ƶ�List
	 * @author Administrator
	 *
	 */
	
	public class player{
		int ID;
		String name;
		List<cards> list;

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			player other = (player) obj;
			if (ID != other.ID)
				return false;
			return true;
		}
		
		
		public boolean equals1(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			player other = (player) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		player(){
			this.list=new ArrayList<cards>();
		}
		player(int ID,String name){
			this.ID=ID;
			this.name=name;
			this.list=new ArrayList<cards>();
		}
	}
	/**
	 * ������ʱ�Ƚ���
	 * @author Administrator
	 *
	 */
	
	public class Compara implements Comparator<cards>{
		@Override
		public int compare(cards o1, cards o2) {
				String color="���� ���� ÷�� ����";
				String number="2 3 4 5 6 7 8 9 10 J Q K A";
				int i=number.indexOf(o1.number)-number.indexOf(o2.number);
				if(i>0){
					return 1;
				}else if(i<0){
					return -1;
				}else{
					int j=color.indexOf(o1.color)-number.indexOf(o2.color);
					if(j>0){
						return -1;
					}else if(j<0){
						return 1;
					}else{
						return 0;
					}
					}
				}
	}
	
	/**
	 * �����˿���
	 */
	
	
	public void createcard(){
		String[] number={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		String[] color={"����","����","÷��","����"};
		System.out.println("-------------�����˿���---------------");
		for(int i=0;i<number.length;i++){
			for(int j=0;j<color.length;j++){
				cards cds=new cards(number[i],color[j]);
				card.add(cds);
			}	
		}
		System.out.println("--------------�������-------------");
		for (cards cad : card) {
			System.out.print(cad.color+cad.number+",");
		}
	}
	
	/**
	 * �������
	 */
	
	public void createplayer(){
		System.out.println("--------------�������------------");
		Scanner in=new Scanner(System.in);
		for(int m=0;m<1;){
			try{
				System.out.println("�������1��ID�� ");
				player1.ID=in.nextInt();
				}catch(InputMismatchException ex){
					in.next();//�ѻ����еĴ������ݶ�ȡ���Ա��������롣
					System.out.println("������󣡣�ID����Ϊ����");
					continue;
			}
			m++;
			}
		System.out.println("�������1�������� ");
		player1.name=in.next();
		for(int m=0;m<1;){
			try{
				System.out.println("�������2��ID�� ");
		    	player2.ID=in.nextInt();
		    	if(player1.equals1(player2)){
					do{
						System.out.println("����ID�Ѵ��ڣ����������룺 ");
						player2.ID=in.nextInt();
					}while(player1.equals1(player2));
				}
				}catch(InputMismatchException ex){
					in.next();//�ѻ����еĴ������ݶ�ȡ���Ա��������롣
					System.out.println("������󣡣�ID����Ϊ����");
					continue;
			}
			m++;
			}
		System.out.println("�������2�������� ");
		player2.name=in.next();
		if(player1.equals(player2)){
			do{
				System.out.println("���������Ѵ��ڣ����������룺 ");
				player2.name=in.next();
			}while(player1.name.equals(player2.name));
		}
	}
	
	/**
	 * ϴ��
	 */
	
	public void stc(){
		System.out.println("--------------ϴ��--------------");
		Collections.shuffle(card);
		System.out.println("--------------ϴ�����-----------");
	}
	/**
	 * ����
	 */
	
	public void licensing(){
		System.out.println("-------------��ʼ����------------");
		for(int i=0;i<4;i++){
			if(i%2==0){
				System.out.println("------------���"+player1.name+"����----------");
				player1.list.add(card.get(i));
			}else{
				System.out.println("------------���"+player2.name+"����----------");
				player2.list.add(card.get(i));
			}
		}
	}
	
	/**
	 * ������ҵ���
	 */
	public void foreach(){
		System.out.println("���"+player1.name+"����Ϊ�� ");
		for (cards cad : player1.list) {
			System.out.println(cad.color+cad.number);
		}
		System.out.println("���"+player2.name+"����Ϊ�� ");
		for (cards cad : player2.list) {
			System.out.println(cad.color+cad.number);
		}
	}
	
	/**
	 * �Ƚ���ҵ���ȷ����ʤ�����
	 */
	
	public void comparetest(){
		List<cards> cop1=new ArrayList<cards>();
		cop1.addAll(player1.list);
		Collections.sort(cop1, new Compara());
		System.out.println("���"+player1.name+"�������Ϊ: "+cop1.get(1).color+cop1.get(1).number);
		List<cards> cop2=new ArrayList<cards>();
		cop2.addAll(player2.list);
		Collections.sort(cop2, new Compara());
		System.out.println("���"+player2.name+"�������Ϊ: "+cop2.get(1).color+cop2.get(1).number);
		List<cards> cop3=new ArrayList<cards>();
		cop3.add(player1.list.get(1));
		cop3.add(player2.list.get(1));
		Collections.sort(cop3, new Compara());
		if(cop3.get(1).equals(player1.list.get(1))){
			System.out.println("��ʤ��Ϊ�� "+player1.ID+":"+player1.name );
		}else{
			System.out.println("��ʤ��Ϊ�� "+player2.ID+":"+player2.name );
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game=new Game();
		game.createcard();
		game.createplayer();
		game.stc();
		game.licensing();
		game.foreach();
		game.comparetest();

	}


}
