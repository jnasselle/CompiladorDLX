import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EvalVisitor1 extends cRestringidoBaseVisitor<String> {

	

	/** declaracionFunc
			: TIPO_ID ID '(' parametrosFormales? ')' bloqueInstrucciones // int main() {...}
			;
	*/
	public String visitDeclaracionFunc(cRestringidoParser.DeclaracionFuncContext ctx) {
		visit(ctx.bloqueInstrucciones());
		return "0";
	}

	/** bloqueInstrucciones
			: '{' tipoInstruccion* '}' # BloqueInstrucciones_Bloque
			;
	*/
	public String visitBloqueInstrucciones_Bloque(cRestringidoParser.BloqueInstrucciones_BloqueContext ctx) {
		System.out.println("BloqueInstrucciones" + " {");
		if(ctx.tipoInstruccion() != null) {
			List<cRestringidoParser.TipoInstruccionContext> instructionTypeList = ctx.tipoInstruccion();
			for(int i = 0; i < instructionTypeList.size(); i++){
				visit(instructionTypeList.get(i)); // Llama a la funcion "visit" que sea necesaria, dependiendo de "tipoInstruccion"
			}
		}
		System.out.println("} FIN BloqueInstrucciones");
		return "0";
	}



	/** tipoInstruccion
			: PUNTO_COMA                # TipoInstruccion_Punto_Coma
			| sentenciaIf               # TipoInstruccion_SentenciaIf
			| sentenciaFor              # TipoInstruccion_SentenciaFor
			| sentenciaWhile            # TipoInstruccion_SentenciaWhile
			| operacionMat PUNTO_COMA   # TipoInstruccion_OperacionMat
			| asignacionVar PUNTO_COMA  # TipoInstruccion_AsignacionVar
			| declaracionVar PUNTO_COMA # TipoInstruccion_DeclaracionVar <-----
			;
	*/
	public String visitTipoInstruccion_DeclaracionVar(cRestringidoParser.TipoInstruccion_DeclaracionVarContext ctx) {
		System.out.println("DeclaracionVar" + " {");
		visit(ctx.declaracionVar());
		System.out.println("} FIN DeclaracionVar");
		return "0";
	}

	/** declaracionVar
			: TIPO_ID subDeclaracionVar (',' subDeclaracionVar)* # DeclaracionVar_Variables
			;
	*/
	public String visitDeclaracionVar_Variables(cRestringidoParser.DeclaracionVar_VariablesContext ctx) {
		System.out.println("Variables" + " {");
		String idType = ctx.TIPO_ID().getText();
		System.out.println(idType);
		for(int i = 0; i < ctx.subDeclaracionVar().size(); i++){
			String id = visit(ctx.subDeclaracionVar(i));
			if(!this.tablaSimbolo.agregarSimbolo(id, idType)) {
				System.out.println("TERMINAR ANALISIS");
			}
		}
		System.out.println("} FIN Variables");
		return "0";
	}

	/** subDeclaracionVar
			: ID ('=' tipoDeclaracionVar)? # SubDeclaracionVar_Id_Igual_TipoDeclVar
			;
	*/
	public String visitSubDeclaracionVar_Id_Igual_TipoDeclVar(cRestringidoParser.SubDeclaracionVar_Id_Igual_TipoDeclVarContext ctx) {
		return ctx.ID().getText();
	}

}
