import java.io.*;  
import java.net.*;  
public class MyServer { 

	int[] divid, divis;
        MyServer()
        {
               // divid = new int[131];
                divis = new int[32];
        }

 	int xorop(int a, int b)
        {
                if(a == 0)
                        return b;
                else if(b == 0)
                        return a;
                else if(a == 1)
                {
                        if(b == 0)
                                return 1;
                        else
                                return 0;
                }
                else
                {
                        if(a == 0)
                                return 1;
                        else
                                return 0;
                }
        }

	void disp(int a[])
        {
                System.out.printf("%d\n", a.length);
                for(int i = 0;i<a.length;i++)
                        System.out.printf("%d", a[i]);
                System.out.printf("\n");
        }


	void checker(int divid[])
        {
                disp(divid);
                int i, j, k, l, m, x, y;
                int[] c_div = new int[divis.length];
                int[] r_div = new int[divis.length-1];
                /*for(i = 0 ;i<divis.length;i++)
                {
                        c_div[i] = divid[i];
                        if(i < divis.length-1)
                                r_div[i] = 0;
                }
                for(j = divis.length-1;j<divid.length;j++, i++)
                {
                        if(c_div[0] == 1)
                        {
                                for(k = 1, y  = 0;k<divis.length;k++, y++)
                                {
                                        r_div[y] =  xorop(c_div[k],divis[k]);
                                }
                        }
                        else
                        {
                                for(l = 1, m = 0;l<c_div.length;l++, m++)
                                        r_div[m] = c_div[l];
                                //r_div[m] = divid[i];
                        }
                        for(x = 0;x<r_div.length;x++)
                                c_div[x] = r_div[x];
                        if(i<divid.length)
                                c_div[x] = divid[i];
                }


                for(i = r_div.length-1,j = divid.length-1;i>=0;i--,j--)
                {
                        divid[j] = r_div[i];
                }

               System.out.printf("\nReduntant bits are: ");
               disp(r_div);
               System.out.printf("\nModified Dividend: ");
               disp(divid);*/

                for(i = 0 ;i<divis.length;i++)
                {
                        c_div[i] = divid[i];
                        if(i < divis.length-1)
                                r_div[i] = 0;
                }
                for(j = divis.length-1;j<divid.length;j++, i++)
                {
                        if(c_div[0] == 1)
                        {
                                for(k = 1, y  = 0;k<divis.length;k++, y++)
                                {
                                        r_div[y] =  xorop(c_div[k],divis[k]);
                                }
 			}
                        else
                        {
                                for(l = 1, m = 0;l<c_div.length;l++, m++)
                                        r_div[m] = c_div[l];
                                //r_div[m] = divid[i];
                        }
                        for(x = 0;x<r_div.length;x++)
                                c_div[x] = r_div[x];
                        if(i<divid.length)
                                c_div[x] = divid[i];
                }

                int flag = 1;
                for(i = 0;i<r_div.length;i++)
                {
                        if(r_div[i] != 0)
                        {
                                flag = 0;
                                break;
                        }

                }

               System.out.printf("\nError Checking Remainder bits are: ");
               disp(r_div);

                if(flag == 0)
                        System.out.printf("\nError Detected!!!");
                else
                        System.out.printf("\nSUCCESS!!!");


	       System.out.printf("\n\n\n");
        }
	
	public static void main(String[] args){  
		try{  
    			ServerSocket ss=new ServerSocket(6666);  
    			Socket s=ss.accept();//establishes connection   
    			ObjectInputStream dis=new ObjectInputStream(s.getInputStream());
			MyServer obj = new MyServer();
			
			obj.divis = (int[])dis.readObject();
			int[][] temp = new int[10][131];

			Integer cnt = (Integer)dis.readObject();

			int[][] divid0 = (int[][])dis.readObject();

			
			for(int i=0 ;i<cnt;i++)
			{
				System.out.printf("\n\n");
				for(int j =0;j<divid0[i].length;j++)
				{
					System.out.printf("%d ", divid0[i][j]);
				}
			}
			
			for(int i=0 ;i<10;i++)
			{
				try{
					System.out.printf("\nChecking %d Packet",i+1);
					Thread.sleep(1000);
					System.out.printf(".");
					Thread.sleep(1000);
					System.out.printf(".");
				}catch(InterruptedException e1){}
				obj.checker(divid0[i]);
			}

    			ss.close();
			dis.close();  
    		}catch(Exception e){System.out.println(e);}  
    	}  
}  
