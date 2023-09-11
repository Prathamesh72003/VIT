%start program
%token NUM
%left '+' '-'
%left '*' '/'

%%
program: /* empty */ | program statement '\n' { $$ = $1; }
statement: expr { printf("Result: %f\n", $1); }
expr: NUM     { $$ = $1; }
    | expr '+' expr  { $$ = $1 + $3; }
    | expr '-' expr  { $$ = $1 - $3; }
    | expr '*' expr  { $$ = $1 * $3; }
    | expr '/' expr  { $$ = $1 / $3; }
    | '(' expr ')'   { $$ = $2; }
    ;
%%

#include <stdio.h>
int yylex();
void yyerror(const char* s);

int main() {
    yyparse();
    return 0;
}

void yyerror(const char* s) {
    fprintf(stderr, "Error: %s\n", s);
}
