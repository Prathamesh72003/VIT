import graphviz
import ply.yacc as yacc

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
    # Assuming you have already defined the yacc parser and have a parse tree
    yacc.parse('2 + 3 * (4 - 1) / 2')  # Replace with your input
    parse_tree = yacc.result

    dot = graphviz.Digraph(format='png')  # Create a Graphviz Dot object
    visualize_tree(parse_tree, dot)  # Visualize the parse tree
    dot.render('parse_tree')  # Render and save the tree as 'parse_tree.png'
