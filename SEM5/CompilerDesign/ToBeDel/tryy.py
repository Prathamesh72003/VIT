import ply.yacc as yacc
import graphviz

# Define the tokens for the lexer (you can define more as needed)
tokens = (
    'NUM',
    'PLUS',
    'MINUS',
    'TIMES',
    'DIVIDE',
    'LPAREN',
    'RPAREN',
)

# Define the precedence and associativity of operators
precedence = (
    ('left', 'PLUS', 'MINUS'),
    ('left', 'TIMES', 'DIVIDE'),
)

# Define the Yacc parser rules
def p_expression(p):
    '''expression : NUM
                  | expression PLUS expression
                  | expression MINUS expression
                  | expression TIMES expression
                  | expression DIVIDE expression
                  | LPAREN expression RPAREN'''
    if len(p) == 2:
        p[0] = p[1]
    elif p[2] == '+':
        p[0] = p[1] + p[3]
    elif p[2] == '-':
        p[0] = p[1] - p[3]
    elif p[2] == '*':
        p[0] = p[1] * p[3]
    elif p[2] == '/':
        p[0] = p[1] / p[3]

# Build the Yacc parser
yacc.yacc()

# Define a function to recursively create Graphviz nodes and edges from the parse tree
def visualize_tree(node, dot):
    if isinstance(node, tuple):
        label = str(node[0])  # Assuming node[0] contains the node label
        dot.node(label)
        for child in node[1:]:
            child_label = str(child[0])
            dot.edge(label, child_label)
            visualize_tree(child, dot)
    elif isinstance(node, int) or isinstance(node, float):
        label = str(node)
        dot.node(label)

# Example usage
if __name__ == "__main__":
    input_expr = '2 + 3 * (4 - 1) / 2'  # Replace with your input
    parse_tree = yacc.parse(input_expr)  # Parse the input

    dot = graphviz.Digraph(format='png')  # Create a Graphviz Dot object
    visualize_tree(parse_tree, dot)  # Visualize the parse tree
    dot.render('parse_tree')  # Render and save the tree as 'parse_tree.png'
