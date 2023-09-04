%{
    #include<stdio.h>
    int num, r, digit=0, count, scount=0, i;
    char a[20];
%}
  
DIGIT [0-9]
%%
  
{DIGIT}+ { num=atoi(yytext);
  
        while(num!=0)
        {
  
            r=num%16;
            digit='0'+r;
            if(digit>'9')
            digit+=7;
            a[count++]=digit;
            num=num/16;
  
        }
  
        for(i=count-1;i>=scount;--i)
                printf("%c", a[i]);
                scount=count;
        
        }
  
.|\n    ECHO;
         
%%

int main()
{
    yylex();
    return 0;
}