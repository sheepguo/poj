/*
poj:1012 Joseph
The Joseph's problem is notoriously known. For those who are not familiar with the original problem: from among n people, numbered 1, 2, . . ., n, standing in circle every mth is going to be executed and only the life of the last remaining person will be saved. Joseph was smart enough to choose the position of the last remaining person, thus saving his life to give us the message about the incident. For example when n = 6 and m = 5 then the people will be executed in the order 5, 4, 6, 2, 3 and 1 will be saved. 

Suppose that there are k good guys and k bad guys. In the circle the first k are good guys and the last k bad guys. You have to determine such minimal m that all the bad guys will be executed before the first good guy. 

author:guoz
date:2015-11-13
*/

/*
思路：
对m从小到大遍历，如果能够满足要求，则停止跳出循环；
对于某个m值，如何判断是否满足要求：
若s1    =   m%(2k) 属于  [k+1,2k) and {0} ，继续，反之跳出；
若s2    =   (s1-1+m)%(2k-1)     属于    [k+1,2k-1)  and {0},    继续，反之跳出；
.
.
.
若sk    =                                                       表示该m符合要求
*/

/*
时间复杂度分析：
最消耗时间的地方：
1、对于给定的m进行check，平均需要循环k/2次才能给出结果
2、对于m从小到大进行尝试，但是其中不是每一个m都得尝试；

针对以上，分别的解决方案：
1、使用数组memory进行记忆，如果之前有算过，就不用再重复计算；
2、如果m%(2*k)==1，就把m=m+k;

*/

import  java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        int[]   memory  =   new int[13];    //对于已经求过的值进行保存
        int i;
        for(i=0;i<13;i++) memory[i] =   0;
        Main    ma  =   new Main();
        int m,k;     //remain是每次的除数，表示剩余的活着人数
        Scanner cin =   new Scanner(System.in);
        while((k=cin.nextInt())!=0)
        {
            if(memory[k-1]    !=  0)  
            {
                System.out.println(memory[k-1]);
                continue;
            }
            m        =   k+1;    //m的最小值是k+1
            if(m%(2*k)==1) m=m+k;
            while(true) //不断将m++，寻找合适的最小m值
            {
                if(ma.check(k,m))  
                {
                    System.out.println(m);
                    memory[k-1] =   m;
                    break;
                }
                else
                    m++;
            }
        }
        return;
    }

    private boolean check(int   k,  int m)  //检查该数值m能否满足要求
    {
        int remain   =   2*k;
        int s=0;
        while(remain>k)
        {
            if(s!=0)
                s=(s-1+m)%remain;
            else
                s=m%remain;
            if(s==0 || s>k) remain--;
            else    return  false;
        }
        return  true;
    }




}
