package ray;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;

class ChessDemo extends Panel implements ActionListener{
	char whoTurn = 'O';  //�ˡ�O,�����--X
	Button b[] = new Button[9];  //9����ť
	StringBuffer chess = new StringBuffer("KKKKKKKKK");
     //���Ź�����һλ��������ʾ,��K�ַ���ʾ��λ��
	 
public ChessDemo(){
      setLayout(new GridLayout(3,3,3,3));
      for (int i=0;i<9;i++) {
          b[i] = new Button("");
          add(b[i]);
          b[i].setActionCommand(String.valueOf(i));
          b[i].addActionListener(this);
      }
}
 
public void actionPerformed(ActionEvent e) {
       Button me = (Button)(e.getSource());//�Լ�
       if (!me.getLabel().equals("")) //����������������λ������ 
			return;
       me.setLabel("" + whoTurn); //�������
       int row = Integer.parseInt(me.getActionCommand()); //��λ��
       chess.setCharAt(row,whoTurn); //��¼����     
       gameOver();  //����Ϸ�Ƿ����
       whoTurn = (whoTurn=='O') ? 'X' : 'O'; //�ֻ����
       computerTake();  //�������
} 
 
public  int  findplace() { //�����������λ��
       for (int r=0;r<9;r++) 
          if (chess.charAt(r)=='K') {    //�Ҹ���λ��
             chess.setCharAt(r,whoTurn);     //����������
             if (isWin(whoTurn))  { //���Լ��´�λ���Ƿ���Ӯ 
                 chess.setCharAt(r,'K');  // �ָ�ԭ״
					return r;
             }
             else
                 chess.setCharAt(r,'K');   // �ָ�ԭ״
         }
     // û�Լ���ֱ��Ӯ��λ���ٿ��Է���Ӯ�ĵط�
      char  whoTurn2 = (whoTurn=='O') ? 'X' : 'O'; //���ɶԷ�˼��
      for (int r=0;r<9;r++) 
			if (chess.charAt(r)=='K') {
             chess.setCharAt(r,whoTurn2);   //�ڿհ״����϶Է�����
             if (isWin(whoTurn2)) {  //���������Ӻ���Ӯ��
                 chess.setCharAt(r,'K');  // �ָ�ԭ״
					return r;
             }
             else
                  chess.setCharAt(r,'K');   //�ָ�ԭ״
         }
			if (chess.charAt(4)=='K') {   //���ȿ�����λ��
            return 4;      //ռ�����룬����4
		}
			else  {
				for (int d=1;d<5;d++) {   // ����Ҹ���λ��
					int rand= (int)(Math.random() * 9);
					if (chess.charAt(rand)=='K')
					return rand;
				}
		}
       return -1;  //���ⷵ��
}
 
public void computerTake(){
     int x = findplace();  //���ݲ�����λ��
     chess.setCharAt(x,whoTurn);
     b[x].setLabel(String.valueOf(whoTurn));
     gameOver();    
     whoTurn = (whoTurn=='O') ? 'X' : 'O';
}
 
public void gameOver() {
   if (isWin(whoTurn)) { //���Ƿ�ȡʤ
       JOptionPane.showMessageDialog(null, whoTurn+" win!");
       System.exit(0);
    } else if (isFull()) { //���Ƿ���������
       JOptionPane.showMessageDialog(null, "game is over!");
       System.exit(0);
    }
 }
 
public boolean isWin(char who){   
   String s3 = "" + who + who + who;
   String sum;   
   String sum1;
   String sum2;//����ƴ��һ����������ӱ�ʶ
   for(int k=0;k<3;k++)
   {
	   sum1=""+chess.charAt(k)+chess.charAt(k+3)+chess.charAt(k+6);//��ֱ����
	   sum2=""+chess.charAt(k*3+0)+chess.charAt(k*3+1)+chess.charAt(k*3+2);//ˮƽ����
	   if(sum1.equals(s3)||sum2.equals(s3))
	   {
		   return true;
	   }
	   else
	   {
		   sum1="";
		   continue;
	   }
	}
   sum=""+chess.charAt(0)+chess.charAt(4)+chess.charAt(8); //���Խ���
   if (sum.equals(s3)) return true;
   sum=""+chess.charAt(2)+chess.charAt(4)+chess.charAt(6); //���Խ���
   if (sum.equals(s3)) return true;
   return false;
}
 
public boolean isFull() {  //���Ƿ�����������
   return chess.toString().indexOf("K")== -1;
}
 
public static void main(String args[]) {
    Frame f = new Frame();
    f.add(new ChessDemo());
    f.setSize(300,300);
    f.setVisible(true); 
}
}
