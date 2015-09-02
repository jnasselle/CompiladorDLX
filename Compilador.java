import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

public class Compilador {

	public static void main(String[] args) throws Exception {
		String inputFile = null;
		if(args.length > 0)
			inputFile = args[0];
		InputStream myInputStream = System.in;
		if(inputFile != null)
			myInputStream = new FileInputStream(inputFile);
		ANTLRInputStream antlrInputStream = new ANTLRInputStream(myInputStream);
		cRestringidoLexer myLexer = new cRestringidoLexer(antlrInputStream);
		CommonTokenStream myTokens = new CommonTokenStream(myLexer);
		cRestringidoParser myParser = new cRestringidoParser(myTokens);
		ParseTree myTree = myParser.cRestringido(); // parse; start at cRestringido
		EvalVisitor eval = new EvalVisitor();
		eval.visit(myTree);
		System.exit(0);
	}
}
