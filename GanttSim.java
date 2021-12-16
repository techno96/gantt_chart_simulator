import java.applet.*;
import java.awt.*;
//import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.lang.*;
//import java.util.Random;

/*
<applet code="GanttSim" height=500 width=700>
</applet>
*/

public class GanttSim extends Applet 
{
	int Choice;
	int apw1[],turn[],bt[],bt1[],wt[],wt1[],tt[],apw[];
	int p[];
	int i=0,n,n1;
	float avg1;
	float avg;
	double awt,att;

Graphics g= getGraphics();
//Button xxx;
public void init()
{
//xxx=new Button("See chart");
//xxx.addActionListener(this);
//add(xxx);
try
{
BufferedReader obj=new BufferedReader(new InputStreamReader (System.in));
System.out.println("Enter the number of processes:");
n=Integer.parseInt(obj.readLine());
int ch;
int bt[]=new int[n];



do{

  System.out.println("GANTT CHART SIMULATOR");
	System.out.println("1-FCFS");
	System.out.println("2-SHORTEST JOB FIRST(non-preemptive)");
	System.out.println("3-SHORTEST JOB FIRST(preemptive)");
	System.out.println("4-ROUND ROBIN");
	System.out.println("5-PRIORITY(non-preemptive)");
	System.out.println("6-PRIORITY(preemptive)");
	System.out.println("7-EXIT");
	System.out.println("Please enter your choice");

        ch=Integer.parseInt(obj.readLine());

	switch(ch)
	{
		case 1:

			{

			for( i=0;i<n;i++)
			{System.out.println("Enter burst time for each process: p"+(i+1));
 			bt[i]=Integer.parseInt(obj.readLine());
			}
			n1=n;
			turn=new int[n];
			apw1=new int[n+1];
			apw1[0]=0;
			float t=0;
			float tu=0;
			p=new int[n];
			for( i=1;i<=n;i++)
			apw1[i]=bt[i-1]+apw1[i-1];
	
			for(i=0;i<n;i++)
			{
			p[i]=i+1;
			//System.out.print(p[i]);
                	}

			for( i=0;i<n;i++)
			t+=apw1[i];

			avg=t/n;
			for( i=0;i<n;i++)
			{
			turn[i]=bt[i]+apw1[i];
			}

			for( i=0;i<n;i++)
			tu+=turn[i];

			avg1=tu/n;

                        Choice=1;
			paint(g);
			}



			break;

		case 2:
			{
			for( i=0;i<n;i++)
			{System.out.println("Enter burst time for each process: p"+(i+1));
 			bt[i]=Integer.parseInt(obj.readLine());
			}
			n1=n;
			int temp;
			float t=0;
			turn=new int[n];
			apw1=new int[n+1];
			apw1[0]=0;
			p=new int[n];
			int sj[]=new int[n];
			float tu=0;

			for( i=0;i<n;i++)
			sj[i]=bt[i];

			for( i=0;i<n;i++)
			for(int j=i+1;j<n;j++)
			if(sj[i]>sj[j])
			{
			temp=sj[i];
			sj[i]=sj[j];
			sj[j]=temp;
			}



			for( i=0;i<n;i++)
			for(int j=0;j<n;j++)
			if(sj[i]==bt[j])
			p[i]=j+1;

			for(i=0;i<n;i++)
			apw1[i+1]=sj[i]+apw1[i];

			

			for( i=0;i<n;i++)
			 t+=apw1[i];
			avg=t/n;
			

			for( i=0;i<n;i++)
			{
			int k=p[i];
			turn[i]=bt[k-1]+apw1[i];
			}
			
			for( i=0;i<n;i++)
			tu+=turn[i];

			avg1=tu/n;
			

                        Choice=2;
			
	paint(g);

			}


			break;

	case 3:
				{

				int total=0,k=0,co=0,small=999,sp=0,sp1=0,x=0,count=0;
				int pro[][]=new int[n][3];
				wt=new int[n];
				wt1=new int[n];
				bt1=new int[n];
				tt=new int[n];
			 	apw1=new int [20];
			 	p=new int[20];

				for(int i=0;i<n;i++)
				{
				pro[i][0]=i;
				System.out.print("Burst Time for P"+i+" :");
				pro[i][1]=Integer.parseInt(obj.readLine());
				total+=pro[i][1];
				System.out.print("Arrival Time for P"+i+" :");
				pro[i][2]=Integer.parseInt(obj.readLine());
				}
				for(int i=0;i<n;i++)
				wt1[i]=pro[i][2];

				for(int i=0;i<n;i++)
				bt1[i]=pro[i][1];

				for(int i=0;i<n;i++)
				for(int j=i+1;j<n;j++)
					if(pro[i][2]>pro[j][2])
					{
						int temp[]=pro[i];
						pro[i]=pro[j];
						pro[j]=temp;
					}

				for(int i=1;i<=total;i++)
				{
				small=999;
				for(int j=co;j<n;j++)
					if(k>=pro[j][2])
					co++;
				for(int j=0;j<co;j++)
				{
					if(small>pro[j][1]&&pro[j][1]!=0)
					{
						small=pro[j][1];
						sp=pro[j][0];
						sp1=j;
					}
				}
				if(p[x]==sp)
				{
				apw1[x+1]++;

				}
				else
				{
				x++;
				p[x]=sp;
				apw1[x+1]=apw1[x];
				apw1[x+1]++;
				count++;
				}



				pro[sp1][1]-=1;
				if(pro[sp1][1]==0)
				tt[sp1]=i;
				for(int j=0;j<n;j++)
				{
					if(pro[j][1]!=0&&j!=sp)
						wt[j]+=1;
				}
				k++;
				}
				for(int i=0;i<n;i++)
				for(int j=i+1;j<n;j++)
					if(pro[i][0]>pro[j][0])
					{
						int temp[]=pro[i];
						pro[i]=pro[j];
						pro[j]=temp;
						int tem=wt[i],tem1=tt[i];
						wt[i]=wt[j];
						wt[j]=tem;
						tt[i]=tt[j];
						tt[j]=tem1;

					}

				System.out.println();

				for(int m=0;m<=count;m++)
				p[m]+=1;


				 awt=0.0;
				 att=0.0;

				System.out.println();
				for (int i = 0; i<n; i++)
				{
				//System.out.println("waiting time for process p "+i+"  "+(wt[i]-wt1[i]));
				awt+=(wt[i]-wt1[i]);
				att+=(wt[i]-wt1[i]+bt1[i]);
				}
				//for (int i = 0; i<n; i++)
				//{
				//System.out.println("turnaround time for process p"+i+"  "+(wt[i]-wt1[i]+bt1[i]));
				//}
				//System.out.println("Average waiting time : "+(awt/n));
				//System.out.println("Average turnaround time : "+(att/n));
				n1=count+1;
                                Choice=3;
				paint(g);

				}
			break;

		case 4:
					{
					for( i=0;i<n;i++)
					{System.out.println("Enter burst time for each process: p"+(i+1));
		 			bt[i]=Integer.parseInt(obj.readLine());
					}
					int ro[]=new int[n];
					apw1=new int[20];
		                	apw1[0]=0;

					int sq[]=new int[20];

					int temp;
					float t=0;
					float tu=0;
					apw=new int[n];
					int bttime=0;
					turn=new int[n];
					int f=0;

					for(i=0;i<n;i++)
					{
					ro[i]=bt[i];

					}


					p=new int[30];
					System.out.println("Enter TIME QUANTUM");
					int ts=Integer.parseInt(obj.readLine());
					int k=0;
					sq[k]=0;
					i=0;
					temp=ts;

					int count=0;

					do
					{

					if(ro[i]==0)
		       			{
			 		i++;
					sq[k]=sq[k-1]+temp;
					}

					else
		       			if(ro[i]>=ts)
					{
					ro[i]=ro[i]-ts;
					p[k]=i+1;
					k++;
					i++;
					temp=ts;
					sq[k]=sq[k-1]+temp;
					turn[i-1]=sq[k];

		     			f=0;
					for(int m=0;m<n;m++)
					f+=ro[m];

					count++;
					}

		       			else
					if(ro[i]>0 && ro[i]<ts)
					{
			  		temp=ro[i];
			  		ro[i]=0;
					p[k]=i+1;
					i++;
					k++;
					sq[k]=sq[k-1]+temp;
					turn[i-1]=sq[k];

					f=0;
					for(int m=0;m<n;m++)
					f+=ro[m];

					count++;

					}

					if(i==n)
					i=0;


					}while(f!=0);


					System.out.println();

					for(i=0;i<=count;i++)
					{
					apw1[i]=sq[i];
					}

					System.out.println();


					for(i=0;i<n;i++)
		   			{
		    			apw[i]=turn[i]-bt[i];
		    			t+=apw[i];
		    			tu+=turn[i];
		   			}

					for(i=0;i<n;i++)
					//System.out.println(" waiting time for p"+(i+1)+"is:"+apw[i]);

					avg=t/n;
					//System.out.println("average waiting time is:"+avg);


					for(i=0;i<n;i++)
					//System.out.println("turn around time is:"+turn[i]);



					avg1=tu/n;
				//	System.out.println("average turn-around time is:"+avg1);


		   			n1=count;


 Choice=4;
					paint(g);



					}
			break;


		case 5:
			{
			for( i=0;i<n;i++)
			{System.out.println("Enter burst time for each process: p"+(i+1));
 			bt[i]=Integer.parseInt(obj.readLine());
			}
			n1=n;
			int pr[]=new int[n];
			int pr1[]=new int[n];
			float t=0;
			int temp;
			turn=new int[n];
			p=new int[n];
			apw1=new int[n+1];
			apw1[0]=0;
			float tu=0;


			for(i=0;i<n;i++)
			{
			System.out.println("Enter the priority for p"+(i+1));
			pr[i]=Integer.parseInt(obj.readLine());
			}

			for(i=0;i<n;i++)
			{
			pr1[i]=pr[i];
			}

			for( i=0;i<n;i++)
			for(int j=i+1;j<n;j++)
			if(pr1[i]>pr1[j])
			{
			temp=pr1[i];
			pr1[i]=pr1[j];
			pr1[j]=temp;
			}

			for( i=0;i<n;i++)
			for(int j=0;j<n;j++)
			if(pr1[i]==pr[j])
			p[i]=j+1;

			for(i=0;i<n;i++)
			{
			int k=p[i];
			apw1[i+1]=bt[k-1]+apw1[i];
			}

			//for( i=0;i<n;i++)
			//{
	//		System.out.println("indivisual waiting time for process p"+p[i]+"is"+apw1[i]+" ");
		//	}

			for( i=0;i<n;i++)
			t+=apw1[i];
			avg=t/n;
//			System.out.println("average waiting time is:"+avg);

			/*for( i=0;i<n;i++)
			System.out.println("response time for process p"+p[i]+"is"+apw1[i]+" ");*/


			for( i=0;i<n;i++)
			{
			int k=p[i];
			turn[i]=bt[k-1]+apw1[i];
			//System.out.println("turnaround time for process p"+p[i]+"is"+turn[i]+" ");
			}

			for( i=0;i<n;i++)
			tu+=turn[i];

			avg1=tu/n;
			//System.out.println("average turn-around time is:"+avg1);

                          Choice=5;
			paint(g);

			}

			break;


		case 6:

			{
			int total=0,k=0,co=0,small=999,sp=0,sp1=0,x=0,count=0;
			int pro[][]=new int[n][4];
			wt=new int[n];
			wt1=new int[n];
			bt1=new int[n];
			tt=new int[n];
		 	apw1=new int [20];
		 	p=new int[20];

			for(int i=0;i<n;i++)
			{
			pro[i][0]=i;
			System.out.print("Burst Time for P"+i+" :");
			pro[i][3]=Integer.parseInt(obj.readLine());
			total+=pro[i][3];
			System.out.print("Arrival Time for P"+i+" :");
			pro[i][2]=Integer.parseInt(obj.readLine());
			System.out.print("Priority for P"+i+" :");
			pro[i][1]=Integer.parseInt(obj.readLine());

			}
			for(int i=0;i<n;i++)
			wt1[i]=pro[i][2];

			for(int i=0;i<n;i++)
			bt1[i]=pro[i][3];

			for(int i=0;i<n;i++)
			for(int j=i+1;j<n;j++)
				if(pro[i][2]>pro[j][2])
				{
					int temp[]=pro[i];
					pro[i]=pro[j];
					pro[j]=temp;
				}

			for(int i=1;i<=total;i++)
			{
			small=999;
			for(int j=co;j<n;j++)
				if(k>=pro[j][2])
				co++;
			for(int j=0;j<co;j++)
			{
				if(small>pro[j][1]&&pro[j][3]!=0)
				{
					small=pro[j][1];
					sp=pro[j][0];
					sp1=j;
				}
			}
			if(p[x]==sp)
			{
			apw1[x+1]++;

			}
			else
			{
			x++;
			p[x]=sp;
			apw1[x+1]=apw1[x];
			apw1[x+1]++;
			count++;
			}



			pro[sp1][3]-=1;
			if(pro[sp1][3]==0)
			tt[sp1]=i;
			for(int j=0;j<n;j++)
			{
				if(pro[j][3]!=0&&j!=sp)
					wt[j]+=1;
			}
			k++;
			}
			for(int i=0;i<n;i++)
			for(int j=i+1;j<n;j++)
				if(pro[i][0]>pro[j][0])
				{
					int temp[]=pro[i];
					pro[i]=pro[j];
					pro[j]=temp;
					int tem=wt[i],tem1=tt[i];
					wt[i]=wt[j];
					wt[j]=tem;
					tt[i]=tt[j];
					tt[j]=tem1;

				}

			System.out.println();

			for(int m=0;m<=count;m++)
			p[m]+=1;


			awt=0.0;
			att=0.0;

			System.out.println();
			for (int i = 0; i<n; i++)
			{
			//System.out.println("waiting time for process p "+i+"  "+(wt[i]-wt1[i]));
			awt+=(wt[i]-wt1[i]);
			att+=(wt[i]-wt1[i]+bt1[i]);
			}
			//for (int i = 0; i<n; i++)
		//	{
			//System.out.println("turnaround time for process p"+i+"  "+(wt[i]-wt1[i]+bt1[i]));
			//}
			//System.out.println("Average waiting time : "+(awt/n));
			//System.out.println("Average turnaround time : "+(att/n));
			n1=count+1;
 Choice=6;
			paint(g);


			



 	}
	}

   } while(ch!=7);




}

catch (Exception e)
{
System.out.println(e);
}
}
//public void actionPerformed(ActionEvent ae)
//{
//	if(Choice>0)
//	paint(g);
//}

public void paint(Graphics g)
{
setBackground(Color.black);
setForeground(Color.green);
Font myF=new Font("Arial",Font.BOLD+Font.ITALIC,20);
g.setFont(myF);
g.drawString("Welcome to the GANTT Chart simulator",50,20);
try
{
Font myFont= new Font("Serif",Font.BOLD,15);
g.setFont(myFont);
for(int j=1;j<=n1;j++)
{
//Random rand= new Random();
//float r=rand.nextFloat();
//float gr=rand.nextFloat();
//float b=rand.nextFloat();
//Color rC=new Color(r,gr,b);

Thread.sleep(1000);
g.setColor(Color.green);
g.drawRect(50,50,(apw1[j]*20),30);
g.setColor(Color.green);
g.drawString("p"+p[j-1],(55+(apw1[j-1]*20)),70);
g.drawString(""+apw1[j-1],50+(apw1[j-1]*20),100);
}
}

catch(Exception e)
{
}

if(Choice==1)
{
	
	
	
try		

	{
for( i=0;i<n;i++)
			{
					Thread.sleep(1000);
			g.drawString("Individual WT - p"+(i+1)+" is "+apw1[i]+" ",50,(i*20)+300);
			}
			for(i=0;i<n;i++){
                            Thread.sleep(1000);
		              
			g.drawString("TT - p"+(i+1)+" is "+turn[i]+" ",225,(i*20)+300);
			}
	}
	
	catch(Exception e)
	{
		
	}
	g.drawString("Average turnaround time is: "+avg1,125,150);
	g.drawString("Average waiting time is: "+avg,125,175);
	
}

if(Choice==2)
{
	
try		

	{
for( i=0;i<n;i++)
			{
					Thread.sleep(1000);
			g.drawString("Individual WT - p"+(i+1)+" is "+apw1[i]+" ",50,(i*20)+300);
			}
			for(i=0;i<n;i++){
                            Thread.sleep(1000);
		              
			g.drawString("TT - p"+(i+1)+" is "+turn[i]+" ",225,(i*20)+300);
			}
	}
	
	catch(Exception e)
	{
		
	}
	
	
	g.drawString("Average turnaround time is: "+avg1,125,150);
	g.drawString("Average waiting time is: "+avg,125,175);
	
}

if(Choice==3)
{
	
try		

	{
for( i=0;i<n;i++)
			{
					Thread.sleep(1000);
			g.drawString("Individual WT - p"+(i+1)+" is "+(wt[i]-wt1[i])+" ",50,(i*20)+300);
			}
			for(i=0;i<n;i++){
                            Thread.sleep(1000);
		              
			g.drawString("TT - p"+(i+1)+" is "+(wt[i]-wt1[i]+bt1[i])+" ",225,(i*20)+300);
			}
	}
	
	catch(Exception e)
	{
		
	}
	
	
	g.drawString("Average turnaround time is: "+(att/n),125,150);
	g.drawString("Average waiting time is: "+(awt/n),125,175);
	
}

if(Choice==4)
{
	
try		

	{
for( i=0;i<n;i++)
			{
					Thread.sleep(1000);
			g.drawString("Individual WT - p"+(i+1)+" is "+apw1[i]+" ",50,(i*20)+300);
			}
			for(i=0;i<n;i++){
                            Thread.sleep(1000);
		              
			g.drawString("TT - p"+(i+1)+" is "+turn[i]+" ",225,(i*20)+300);
			}
	}
	
	catch(Exception e)
	{
		
	}
	
	
	g.drawString("Average turnaround time is: "+avg1,125,150);
	g.drawString("Average waiting time is: "+avg,125,175);
	
}if(Choice==5)
{
	
try		

	{
for( i=0;i<n;i++)
			{
					Thread.sleep(1000);
			g.drawString("Individual WT - p"+(i+1)+" is "+apw1[i]+" ",50,(i*20)+300);
			}
			for(i=0;i<n;i++){
                            Thread.sleep(1000);
		              
			g.drawString("TT - p"+(i+1)+" is "+turn[i]+" ",225,(i*20)+300);
			}
	}
	
	catch(Exception e)
	{
		
	}
	
	
	g.drawString("Average turnaround time is: "+avg1,125,150);
	g.drawString("Average waiting time is: "+avg,125,175);
	
}

if(Choice==6)
{
	
try		

	{
for( i=0;i<n;i++)
			{
					Thread.sleep(1000);
			g.drawString("Individual WT - p"+(i+1)+" is "+(wt[i]-wt1[i])+" ",50,(i*20)+300);
			}
			for(i=0;i<n;i++){
                            Thread.sleep(1000);
		              
			g.drawString("TT - p"+(i+1)+" is "+(wt[i]-wt1[i]+bt1[i])+" ",225,(i*20)+300);
			}
	}
	
	catch(Exception e)
	{
		
	}
	
	
	g.drawString("Average turnaround time is: "+(att/n),125,150);
	g.drawString("Average waiting time is: "+(awt/n),125,175);
	
}

g.drawString(""+apw1[n1],50+(apw1[n1]*20),100);







}
public static void main(String args[])
{
	GanttSim g = new GanttSim();
	g.init();
}
}