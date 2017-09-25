import java.io.*;  
import java.net.*;
import java.util.*;  
public class MyClient {
	
	int[] data, divid, divis;
	MyClient()
        {
                data = new int[1000];
                divid = new int[131];
                divis = new int[32];
        }

        void gen(int a[], int n, int i)
        {
                Random r = new Random(2);
                for(;i<a.length;i++)
                {
                        a[i] = r.nextInt(2);
                }
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
	
	void addCRCBits(int divid[])
        {
                disp(divid);
                int i, j, k, l, m, x, y;
                int[] c_div = new int[divis.length];
                int[] r_div = new int[divis.length-1];
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


                for(i = r_div.length-1,j = divid.length-1;i>=0;i--,j--)
                {
                        divid[j] = r_div[i];
                }

               System.out.printf("\nReduntant bits are: ");
               disp(r_div);
               //System.out.printf("\nModified Dividend: ");
               //disp(divid);
	}

	public static void main(String[] args) {  
		int cnt = 0;
		int cnt1 = 0;
		int[][] tmp = new int[10][131];
		MyClient obj = new MyClient();
                obj.gen(obj.data, obj.data.length, 0);
                obj.divis[0] = 1;
                obj.gen(obj.divis, obj.divis.length, 1);
                obj.disp(obj.data);
                obj.disp(obj.divis);
		try{      
			Socket s=new Socket("localhost",6666);  
			ObjectOutputStream dout=new ObjectOutputStream(s.getOutputStream());
			
			dout.writeObject(obj.divis);

			for(int i = 0;i<obj.data.length; i++)
			{
                        	obj.divid[cnt] = obj.data[i];
				tmp[cnt1][cnt++] = obj.data[i];
                        	if(i%100 == 99)
                        	{
                                	for(int j = i+1; j<=30+i ; j++)
                                	{
                                        	obj.divid[cnt] = 0;
						tmp[cnt1][cnt++] = 0;

                                	}
                               		cnt = 0;
					cnt1+=1;
					System.out.printf("\n%d", cnt1);
					System.out.printf("\nThe Dividend Sent is :\n ");
					obj.disp(tmp[cnt1-1]);
					obj.addCRCBits(tmp[cnt1-1]);
					System.out.printf("\nThe Dividend afetr adding CRC bits:\n");
					obj.disp(tmp[cnt1-1]);
                        	}
                	}
			for(int i=0 ;i<10;i++)
                        {
                                System.out.printf("\n\n");
                                for(int j =0;j<131;j++)
                                {
                                        System.out.printf("%d ", tmp[i][j]);
                                }
                        }
			dout.writeObject(cnt1);
			dout.writeObject(tmp);
			dout.flush();
			dout.close();  
			s.close();  
		}catch(Exception e){System.out.println(e);}  
	}
}  
