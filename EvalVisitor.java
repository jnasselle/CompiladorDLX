import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EvalVisitor extends cRestringidoBaseVisitor<String> {

	TablaSimbolo tablaSimbolo = new TablaSimbolo();

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
			: PUNTO_COMA                # TipoInstruccion_Punto_Coma <-----
			| sentenciaIf               # TipoInstruccion_SentenciaIf
			| sentenciaFor              # TipoInstruccion_SentenciaFor
			| sentenciaWhile            # TipoInstruccion_SentenciaWhile
			| operacionMat PUNTO_COMA   # TipoInstruccion_OperacionMat
			| asignacionVar PUNTO_COMA  # TipoInstruccion_AsignacionVar
			| declaracionVar PUNTO_COMA # TipoInstruccion_DeclaracionVar
			;
	*/
	public String visitTipoInstruccion_Punto_Coma(cRestringidoParser.TipoInstruccion_Punto_ComaContext ctx) {
		String semicolon = ctx.PUNTO_COMA().getText();
		System.out.println(semicolon);
		return "0";
	}

	/** tipoInstruccion
			: PUNTO_COMA                # TipoInstruccion_Punto_Coma
			| sentenciaIf               # TipoInstruccion_SentenciaIf <-----
			| sentenciaFor              # TipoInstruccion_SentenciaFor
			| sentenciaWhile            # TipoInstruccion_SentenciaWhile
			| operacionMat PUNTO_COMA   # TipoInstruccion_OperacionMat
			| asignacionVar PUNTO_COMA  # TipoInstruccion_AsignacionVar
			| declaracionVar PUNTO_COMA # TipoInstruccion_DeclaracionVar
			;
	*/
	public String visitTipoInstruccion_SentenciaIf(cRestringidoParser.TipoInstruccion_SentenciaIfContext ctx) {
		visit(ctx.sentenciaIf());
		return "0";
	}

	/** tipoInstruccion
			: PUNTO_COMA                # TipoInstruccion_Punto_Coma
			| sentenciaIf               # TipoInstruccion_SentenciaIf
			| sentenciaFor              # TipoInstruccion_SentenciaFor <-----
			| sentenciaWhile            # TipoInstruccion_SentenciaWhile
			| operacionMat PUNTO_COMA   # TipoInstruccion_OperacionMat
			| asignacionVar PUNTO_COMA  # TipoInstruccion_AsignacionVar
			| declaracionVar PUNTO_COMA # TipoInstruccion_DeclaracionVar
			;
	*/
	public String visitTipoInstruccion_SentenciaFor(cRestringidoParser.TipoInstruccion_SentenciaForContext ctx) {
		visit(ctx.sentenciaFor());
		return "0";
	}

	/** tipoInstruccion
			: PUNTO_COMA                # TipoInstruccion_Punto_Coma
			| sentenciaIf               # TipoInstruccion_SentenciaIf
			| sentenciaFor              # TipoInstruccion_SentenciaFor
			| sentenciaWhile            # TipoInstruccion_SentenciaWhile <-----
			| operacionMat PUNTO_COMA   # TipoInstruccion_OperacionMat
			| asignacionVar PUNTO_COMA  # TipoInstruccion_AsignacionVar
			| declaracionVar PUNTO_COMA # TipoInstruccion_DeclaracionVar
			;
	*/
	public String visitTipoInstruccion_SentenciaWhile(cRestringidoParser.TipoInstruccion_SentenciaWhileContext ctx) {
		visit(ctx.sentenciaWhile());
		return "0";
	}

	/** tipoInstruccion
			: PUNTO_COMA                # TipoInstruccion_Punto_Coma
			| sentenciaIf               # TipoInstruccion_SentenciaIf
			| sentenciaFor              # TipoInstruccion_SentenciaFor
			| sentenciaWhile            # TipoInstruccion_SentenciaWhile
			| operacionMat PUNTO_COMA   # TipoInstruccion_OperacionMat <-----
			| asignacionVar PUNTO_COMA  # TipoInstruccion_AsignacionVar
			| declaracionVar PUNTO_COMA # TipoInstruccion_DeclaracionVar
			;
	*/
	public String visitTipoInstruccion_OperacionMat(cRestringidoParser.TipoInstruccion_OperacionMatContext ctx) {
		System.out.println("OperacionMat" + " {");
		visit(ctx.operacionMat());
		System.out.println("} FIN OperacionMat");
		return "0";
	}

	/** tipoInstruccion
			: PUNTO_COMA                # TipoInstruccion_Punto_Coma
			| sentenciaIf               # TipoInstruccion_SentenciaIf
			| sentenciaFor              # TipoInstruccion_SentenciaFor
			| sentenciaWhile            # TipoInstruccion_SentenciaWhile
			| operacionMat PUNTO_COMA   # TipoInstruccion_OperacionMat
			| asignacionVar PUNTO_COMA  # TipoInstruccion_AsignacionVar <-----
			| declaracionVar PUNTO_COMA # TipoInstruccion_DeclaracionVar
			;
	*/
	public String visitTipoInstruccion_AsignacionVar(cRestringidoParser.TipoInstruccion_AsignacionVarContext ctx) {
		System.out.println("AsignacionVar" + " {");
		visit(ctx.asignacionVar());
		System.out.println("} FIN AsignacionVar");
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

	/** tipoDeclaracionVar 
			: ID            # TipoDeclaracionVar_Id <-----
			| NUMERO        # TipoDeclaracionVar_Numero
			| CARACTER      # TipoDeclaracionVar_Caracter 
			| operacionMat  # TipoDeclaracionVar_OperacionMat
			;
	*/
	public String visitTipoDeclaracionVar_Id(cRestringidoParser.TipoDeclaracionVar_IdContext ctx) {
		return ctx.ID().getText();
	}

	/** tipoDeclaracionVar 
			: ID            # TipoDeclaracionVar_Id
			| NUMERO        # TipoDeclaracionVar_Numero <-----
			| CARACTER      # TipoDeclaracionVar_Caracter 
			| operacionMat  # TipoDeclaracionVar_OperacionMat
			;
	*/
	public String visitTipoDeclaracionVar_Numero(cRestringidoParser.TipoDeclaracionVar_NumeroContext ctx) {
		return ctx.NUMERO().getText();
	}

	/** tipoDeclaracionVar 
			: ID            # TipoDeclaracionVar_Id
			| NUMERO        # TipoDeclaracionVar_Numero
			| CARACTER      # TipoDeclaracionVar_Caracter <-----
			| operacionMat  # TipoDeclaracionVar_OperacionMat
			;
	*/
	public String visitTipoDeclaracionVar_Caracter(cRestringidoParser.TipoDeclaracionVar_CaracterContext ctx) {
		return ctx.CARACTER().getText();
	}

	/** tipoDeclaracionVar 
			: ID            # TipoDeclaracionVar_Id
			| NUMERO        # TipoDeclaracionVar_Numero
			| CARACTER      # TipoDeclaracionVar_Caracter
			| operacionMat  # TipoDeclaracionVar_OperacionMat <-----
			;
	*/
	public String visitTipoDeclaracionVar_OperacionMat(cRestringidoParser.TipoDeclaracionVar_OperacionMatContext ctx) {
		return visit(ctx.operacionMat());
	}

	/** asignacionVar
			: ID '=' operacionMat # AsignacionVar_Asignacion <-----
			;
	*/
	public String visitAsignacionVar_Asignacion(cRestringidoParser.AsignacionVar_AsignacionContext ctx) {
		System.out.println("Asignacion" + " {");
		String id = ctx.ID().getText();
		System.out.println("ID:" + id);
		visit(ctx.operacionMat());
		System.out.println("} FIN Asignacion");
		return "0";
	}

	/** operacionMat
			: ID                                     # OperacionMat_Id <-----
			| NUMERO                                 # OperacionMat_Numero
			| CARACTER                               # OperacionMat_Caracter
			| incrementoVar                          # OperacionMat_IncrementoVar
			| decrementoVar                          # OperacionMat_DecrementoVar
			| '(' operacionMat ')'                   # OperacionMat_Paren_OpMat_Paren
			| operacionMat OPERADOR_MAT operacionMat # OperacionMat_OpMat_Op_OpMat
			;
	*/
	public String visitOperacionMat_Id(cRestringidoParser.OperacionMat_IdContext ctx) {
		return ctx.ID().getText();
	}

	/** operacionMat
			: ID                                     # OperacionMat_Id
			| NUMERO                                 # OperacionMat_Numero <-----
			| CARACTER                               # OperacionMat_Caracter
			| incrementoVar                          # OperacionMat_IncrementoVar
			| decrementoVar                          # OperacionMat_DecrementoVar
			| '(' operacionMat ')'                   # OperacionMat_Paren_OpMat_Paren
			| operacionMat OPERADOR_MAT operacionMat # OperacionMat_OpMat_Op_OpMat
			;
	*/
	public String visitOperacionMat_Numero(cRestringidoParser.OperacionMat_NumeroContext ctx) {
		return ctx.NUMERO().getText();
	}

	/** operacionMat
			: ID                                     # OperacionMat_Id
			| NUMERO                                 # OperacionMat_Numero
			| CARACTER                               # OperacionMat_Caracter <-----
			| incrementoVar                          # OperacionMat_IncrementoVar
			| decrementoVar                          # OperacionMat_DecrementoVar
			| '(' operacionMat ')'                   # OperacionMat_Paren_OpMat_Paren
			| operacionMat OPERADOR_MAT operacionMat # OperacionMat_OpMat_Op_OpMat
			;
	*/
	public String visitOperacionMat_Caracter(cRestringidoParser.OperacionMat_CaracterContext ctx) {
		return ctx.CARACTER().getText();
	}
///////////////////////////////////////////////// ACAAAAA /////////////////////////////////////////////////////////
	/** operacionMat
			: ID                                     # OperacionMat_Id
			| NUMERO                                 # OperacionMat_Numero
			| CARACTER                               # OperacionMat_Caracter
			| incrementoVar                          # OperacionMat_IncrementoVar <-----
			| decrementoVar                          # OperacionMat_DecrementoVar
			| '(' operacionMat ')'                   # OperacionMat_Paren_OpMat_Paren
			| operacionMat OPERADOR_MAT operacionMat # OperacionMat_OpMat_Op_OpMat
			;
	*/
	public String visitOperacionMat_IncrementoVar(cRestringidoParser.OperacionMat_IncrementoVarContext ctx) {
		visit(ctx.incrementoVar());
		return "0";
	}

	/** operacionMat
			: ID                                     # OperacionMat_Id
			| NUMERO                                 # OperacionMat_Numero
			| CARACTER                               # OperacionMat_Caracter
			| incrementoVar                          # OperacionMat_IncrementoVar
			| decrementoVar                          # OperacionMat_DecrementoVar <-----
			| '(' operacionMat ')'                   # OperacionMat_Paren_OpMat_Paren
			| operacionMat OPERADOR_MAT operacionMat # OperacionMat_OpMat_Op_OpMat
			;
	*/
	public String visitOperacionMat_DecrementoVar(cRestringidoParser.OperacionMat_DecrementoVarContext ctx) {
		visit(ctx.decrementoVar());
		return "0";
	}

	/** operacionMat
			: ID                                     # OperacionMat_Id
			| NUMERO                                 # OperacionMat_Numero
			| CARACTER                               # OperacionMat_Caracter
			| incrementoVar                          # OperacionMat_IncrementoVar
			| decrementoVar                          # OperacionMat_DecrementoVar
			| '(' operacionMat ')'                   # OperacionMat_Paren_OpMat_Paren <-----
			| operacionMat OPERADOR_MAT operacionMat # OperacionMat_OpMat_Op_OpMat
			;
	*/
	public String visitOperacionMat_Paren_OpMat_Paren(cRestringidoParser.OperacionMat_Paren_OpMat_ParenContext ctx) {
		visit(ctx.operacionMat());
		return "0";
	}

	/** operacionMat
			: ID                                     # OperacionMat_Id
			| NUMERO                                 # OperacionMat_Numero
			| CARACTER                               # OperacionMat_Caracter
			| incrementoVar                          # OperacionMat_IncrementoVar
			| decrementoVar                          # OperacionMat_DecrementoVar
			| '(' operacionMat ')'                   # OperacionMat_Paren_OpMat_Paren
			| operacionMat OPERADOR_MAT operacionMat # OperacionMat_OpMat_Op_OpMat <-----
			;
	*/
	public String visitOperacionMat_OpMat_Op_OpMat(cRestringidoParser.OperacionMat_OpMat_Op_OpMatContext ctx) {
		visit(ctx.operacionMat(0));	// Visitamos la subexpresion de la izquierda
		System.out.print(" OPERADOR_MAT:" + ctx.OPERADOR_MAT().getText() + " ");
		visit(ctx.operacionMat(1));	// Visitamos la subexpresion de la derecha
		return "0";
	}

	/** incrementoVar
			: ID '+=' (CARACTER | NUMERO | ID) # IncrementoVar_SumaAsignacion <-----
			| '++' ID                          # IncrementoVar_PreIncremento
			| ID '++'                          # IncrementoVar_PostIncremento
			;
	*/
	public String visitIncrementoVar_SumaAsignacion(cRestringidoParser.IncrementoVar_SumaAsignacionContext ctx) {
		System.out.println("SumaAsignacion" + " {");
		System.out.print("ID:" + ctx.ID(0).getText());
		System.out.print(" += ");
		if(ctx.CARACTER() != null) {
			System.out.println("CARACTER:" + ctx.CARACTER().getText());
		}
		else if(ctx.NUMERO() != null) {
			int number = Integer.parseInt(ctx.NUMERO().getText());
			System.out.println("NUMERO:" + number);
		}
		else {
			System.out.println("ID:" + ctx.ID(1).getText());
		}
		System.out.println("} FIN SumaAsignacion");
		return "0";
	}

	/** incrementoVar
			: ID '+=' (CARACTER | NUMERO | ID) # IncrementoVar_SumaAsignacion
			| '++' ID                          # IncrementoVar_PreIncremento <-----
			| ID '++'                          # IncrementoVar_PostIncremento
			;
	*/
	public String visitIncrementoVar_PreIncremento(cRestringidoParser.IncrementoVar_PreIncrementoContext ctx) {
		System.out.println("PreIncremento" + " {");
		String id = ctx.ID().getText();
		System.out.print("ID:" + id);
		System.out.println();
		System.out.println("} FIN PreIncremento");
		return "0";
	}

	/** incrementoVar
			: ID '+=' (CARACTER | NUMERO | ID) # IncrementoVar_SumaAsignacion
			| '++' ID                          # IncrementoVar_PreIncremento
			| ID '++'                          # IncrementoVar_PostIncremento <-----
			;
	*/
	public String visitIncrementoVar_PostIncremento(cRestringidoParser.IncrementoVar_PostIncrementoContext ctx) {
		System.out.println("PostIncremento" + " {");
		String id = ctx.ID().getText();
		System.out.print("ID:" + id);
		System.out.println("} FIN PostIncremento");
		return "0";
	}

	/** decrementoVar
			: ID '-=' (CARACTER | NUMERO | ID) # DecrementoVar_RestaAsignacion <-----
			| '--' ID                          # DecrementoVar_PreDecremento
			| ID '--'                          # DecrementoVar_PostDecremento
			;
	*/
	public String visitDecrementoVar_RestaAsignacion(cRestringidoParser.DecrementoVar_RestaAsignacionContext ctx) {
		System.out.println("RestaAsignacion" + " {");
		System.out.print("ID:" + ctx.ID(0).getText());
		System.out.print(" -= ");
		if(ctx.CARACTER() != null) {
			System.out.println("CARACTER:" + ctx.CARACTER().getText());
		}
		else if(ctx.NUMERO() != null) {
			int number = Integer.parseInt(ctx.NUMERO().getText());
			System.out.println("NUMERO:" + number);
		}
		else {
			System.out.println("ID:" + ctx.ID(1).getText());
		}
		System.out.println("} FIN RestaAsignacion");
		return "0";
	}

	/** decrementoVar
			: ID '-=' (CARACTER | NUMERO | ID) # DecrementoVar_RestaAsignacion
			| '--' ID                          # DecrementoVar_PreDecremento <-----
			| ID '--'                          # DecrementoVar_PostDecremento
			;
	*/
	public String visitDecrementoVar_PreDecremento(cRestringidoParser.DecrementoVar_PreDecrementoContext ctx) {
		System.out.println("PreDecremento" + " {");
		String id = ctx.ID().getText();
		System.out.print("ID:" + id);
		System.out.println("} FIN PreDecremento");
		return "0";
	}

	/** decrementoVar
			: ID '-=' (CARACTER | NUMERO | ID) # DecrementoVar_RestaAsignacion
			| '--' ID                          # DecrementoVar_PreDecremento
			| ID '--'                          # DecrementoVar_PostDecremento <-----
			;
	*/
	public String visitDecrementoVar_PostDecremento(cRestringidoParser.DecrementoVar_PostDecrementoContext ctx) {
		System.out.println("PostDecremento" + " {");
		String id = ctx.ID().getText();
		System.out.print("ID:" + id);
		System.out.println("} FIN PostDecremento");
		return "0";
	}

	/** sentenciaIf
			: 'if' '(' listaCondicion ')' (bloqueInstrucciones | tipoInstruccion) sentenciaElse? # SentenciaIf_Declaracion
			;
	*/
	public String visitSentenciaIf_Declaracion(cRestringidoParser.SentenciaIf_DeclaracionContext ctx) {
		System.out.println("if" + " (");
		visit(ctx.listaCondicion());
		System.out.println(") FIN Condicion del if");
		// Verificamos "(bloqueInstrucciones | tipoInstruccion)"
		if(ctx.bloqueInstrucciones() != null) {
			visit(ctx.bloqueInstrucciones());
		}
		else {
			visit(ctx.tipoInstruccion());
		}
		// Verificamos "sentenciaElse?"
		if(ctx.sentenciaElse() != null) {
			visit(ctx.sentenciaElse());
		}
		return "0";
	}

	/** listaCondicion
			: operacionCond (',' operacionCond)* # ListaCondicion_Lista
			;
	*/
	public String visitListaCondicion_Lista(cRestringidoParser.ListaCondicion_ListaContext ctx) {
		System.out.println("ListaCondicion" + " {");
		if(ctx.operacionCond().size() > 0) {
			for(int i = 0; i < ctx.operacionCond().size(); i++) {
				visit(ctx.operacionCond(i));
			}
		}
		System.out.println("} FIN ListaCondicion");
		return "0";
	}

	/** operacionCond
			: OPERADOR_NOT? ID                                 # OperacionCond_OpNot_Id <-----
			| OPERADOR_NOT? NUMERO                             # OperacionCond_OpNot_Numero
			| OPERADOR_NOT? CARACTER                           # OperacionCond_OpNot_Caracter
			| OPERADOR_NOT? '(' operacionCond ')'              # OperacionCond_OpNot_Paren_OpCond_Paren
			| operacionCond OPERADOR_LOGICO operacionCond      # OperacionCond_OpCond_OpLogico_OpCond
			| operacionCond OPERADOR_COMPARACION operacionCond # OperacionCond_OpCond_OpComp_OpCond
			| operacionMat                                     # OperacionCond_OperacionMat
			;
	*/
	public String visitOperacionCond_OpNot_Id(cRestringidoParser.OperacionCond_OpNot_IdContext ctx) {
		if(ctx.OPERADOR_NOT() != null) {
			String opNot = ctx.OPERADOR_NOT().getText();
			System.out.print(opNot);
		}
		else {
			String id = ctx.ID().getText();
			System.out.print(id);
		}
		return "0";
	}

	/** operacionCond
			: OPERADOR_NOT? ID                                 # OperacionCond_OpNot_Id
			| OPERADOR_NOT? NUMERO                             # OperacionCond_OpNot_Numero <-----
			| OPERADOR_NOT? CARACTER                           # OperacionCond_OpNot_Caracter
			| OPERADOR_NOT? '(' operacionCond ')'              # OperacionCond_OpNot_Paren_OpCond_Paren
			| operacionCond OPERADOR_LOGICO operacionCond      # OperacionCond_OpCond_OpLogico_OpCond
			| operacionCond OPERADOR_COMPARACION operacionCond # OperacionCond_OpCond_OpComp_OpCond
			| operacionMat                                     # OperacionCond_OperacionMat
			;
	*/
	public String visitOperacionCond_OpNot_Numero(cRestringidoParser.OperacionCond_OpNot_NumeroContext ctx) {
		System.out.println("OpNot_Numero" + " {");
		if(ctx.OPERADOR_NOT() != null) {
			String opNot = ctx.OPERADOR_NOT().getText();
			System.out.print(opNot);
		}
		else {
			int number = Integer.parseInt(ctx.NUMERO().getText());
			System.out.print(number);
		}
		System.out.println("} FIN OpNot_Numero");
		return "0";
	}

	/** operacionCond
			: OPERADOR_NOT? ID                                 # OperacionCond_OpNot_Id
			| OPERADOR_NOT? NUMERO                             # OperacionCond_OpNot_Numero
			| OPERADOR_NOT? CARACTER                           # OperacionCond_OpNot_Caracter <-----
			| OPERADOR_NOT? '(' operacionCond ')'              # OperacionCond_OpNot_Paren_OpCond_Paren
			| operacionCond OPERADOR_LOGICO operacionCond      # OperacionCond_OpCond_OpLogico_OpCond
			| operacionCond OPERADOR_COMPARACION operacionCond # OperacionCond_OpCond_OpComp_OpCond
			| operacionMat                                     # OperacionCond_OperacionMat
			;
	*/
	public String visitOperacionCond_Caracter(cRestringidoParser.OperacionCond_OpNot_CaracterContext ctx) {
		System.out.println("OpNot_Caracter" + " {");
		if(ctx.OPERADOR_NOT() != null) {
			String opNot = ctx.OPERADOR_NOT().getText();
			System.out.print(opNot);
		}
		else {
			String character = ctx.CARACTER().getText();
			System.out.print(character);
		}
		System.out.println("} FIN OpNot_Caracter");
		return "0";
	}

	/** operacionCond
			: OPERADOR_NOT? ID                                 # OperacionCond_OpNot_Id
			| OPERADOR_NOT? NUMERO                             # OperacionCond_OpNot_Numero
			| OPERADOR_NOT? CARACTER                           # OperacionCond_OpNot_Caracter
			| OPERADOR_NOT? '(' operacionCond ')'              # OperacionCond_OpNot_Paren_OpCond_Paren <-----
			| operacionCond OPERADOR_LOGICO operacionCond      # OperacionCond_OpCond_OpLogico_OpCond
			| operacionCond OPERADOR_COMPARACION operacionCond # OperacionCond_OpCond_OpComp_OpCond
			| operacionMat                                     # OperacionCond_OperacionMat
			;
	*/
	public String visitOperacionCond_OpNot_Paren_OpCond_Paren(cRestringidoParser.OperacionCond_OpNot_Paren_OpCond_ParenContext ctx) {
		System.out.println("OpNot_Paren_OpCond_Paren" + " {");
		if(ctx.OPERADOR_NOT() != null) {
			String opNot = ctx.OPERADOR_NOT().getText();
			System.out.println(opNot);
		}
		else {
			System.out.println("(");
			visit(ctx.operacionCond());
			System.out.println(")");
		}
		System.out.println("} FIN OpNot_Paren_OpCond_Paren");
		return "0";
	}

	/** operacionCond
			: OPERADOR_NOT? ID                                 # OperacionCond_OpNot_Id
			| OPERADOR_NOT? NUMERO                             # OperacionCond_OpNot_Numero
			| OPERADOR_NOT? CARACTER                           # OperacionCond_OpNot_Caracter
			| OPERADOR_NOT? '(' operacionCond ')'              # OperacionCond_OpNot_Paren_OpCond_Paren
			| operacionCond OPERADOR_LOGICO operacionCond      # OperacionCond_OpCond_OpLogico_OpCond <-----
			| operacionCond OPERADOR_COMPARACION operacionCond # OperacionCond_OpCond_OpComp_OpCond
			| operacionMat                                     # OperacionCond_OperacionMat
			;
	*/
	public String visitOperacionCond_OpCond_OpLogico_OpCond(cRestringidoParser.OperacionCond_OpCond_OpLogico_OpCondContext ctx) {
		visit(ctx.operacionCond(0)); // Visitamos la subexpresion de la izquierda
		System.out.print(" OPERADOR_LOGICO:" + ctx.OPERADOR_LOGICO().getText() + " ");
		visit(ctx.operacionCond(1)); // Visitamos la subexpresion de la derecha
		System.out.println();
		return "0";
	}

	/** operacionCond
			: OPERADOR_NOT? ID                                 # OperacionCond_OpNot_Id
			| OPERADOR_NOT? NUMERO                             # OperacionCond_OpNot_Numero
			| OPERADOR_NOT? CARACTER                           # OperacionCond_OpNot_Caracter
			| OPERADOR_NOT? '(' operacionCond ')'              # OperacionCond_OpNot_Paren_OpCond_Paren
			| operacionCond OPERADOR_LOGICO operacionCond      # OperacionCond_OpCond_OpLogico_OpCond
			| operacionCond OPERADOR_COMPARACION operacionCond # OperacionCond_OpCond_OpComp_OpCond <-----
			| operacionMat                                     # OperacionCond_OperacionMat
			;
	*/
	public String visitOperacionCond_OpCond_OpComp_OpCond(cRestringidoParser.OperacionCond_OpCond_OpComp_OpCondContext ctx) {
		visit(ctx.operacionCond(0)); // Visitamos la subexpresion de la izquierda
		System.out.print(" OPERADOR_COMPARACION:" + ctx.OPERADOR_COMPARACION().getText() + " ");
		visit(ctx.operacionCond(1)); // Visitamos la subexpresion de la derecha
		System.out.println();
		return "0";
	}

	/** operacionCond
			: OPERADOR_NOT? ID                                 # OperacionCond_OpNot_Id
			| OPERADOR_NOT? NUMERO                             # OperacionCond_OpNot_Numero
			| OPERADOR_NOT? CARACTER                           # OperacionCond_OpNot_Caracter
			| OPERADOR_NOT? '(' operacionCond ')'              # OperacionCond_OpNot_Paren_OpCond_Paren
			| operacionCond OPERADOR_LOGICO operacionCond      # OperacionCond_OpCond_OpLogico_OpCond
			| operacionCond OPERADOR_COMPARACION operacionCond # OperacionCond_OpCond_OpComp_OpCond
			| operacionMat                                     # OperacionCond_OperacionMat <-----
			;
	*/
	public String visitOperacionCond_OperacionMat(cRestringidoParser.OperacionCond_OperacionMatContext ctx) {
		System.out.println("OperacionMat" + " {");
		visit(ctx.operacionMat());
		System.out.println("} FIN OperacionMat");
		return "0";
	}

	/** sentenciaElse
			: 'else' sentenciaElse # SentenciaElse_Declaracion <-----
			| bloqueInstrucciones  # SentenciaElse_BloqueInstrucciones
			| tipoInstruccion      # SentenciaElse_TipoInstruccion
			;
	*/
	public String visitSentenciaElse_Declaracion(cRestringidoParser.SentenciaElse_DeclaracionContext ctx) {
		System.out.println("else" + " {");
		visit(ctx.sentenciaElse());
		System.out.println("} FIN else");
		return "0";
	}

	/** sentenciaElse
			: 'else' sentenciaElse # SentenciaElse_Declaracion
			| bloqueInstrucciones  # SentenciaElse_BloqueInstrucciones <-----
			| tipoInstruccion      # SentenciaElse_TipoInstruccion
			;
	*/
	public String visitSentenciaElse_BloqueInstrucciones(cRestringidoParser.SentenciaElse_BloqueInstruccionesContext ctx) {
		visit(ctx.bloqueInstrucciones());
		return "0";
	}

	/** sentenciaElse
			: 'else' sentenciaElse # SentenciaElse_Declaracion
			| bloqueInstrucciones  # SentenciaElse_BloqueInstrucciones
			| tipoInstruccion      # SentenciaElse_TipoInstruccion <-----
			;
	*/
	public String visitSentenciaElse_TipoInstruccion(cRestringidoParser.SentenciaElse_TipoInstruccionContext ctx) {
		visit(ctx.tipoInstruccion());
		return "0";
	}

	/** sentenciaWhile
			: 'while' '(' listaCondicion ')' (bloqueInstrucciones | tipoInstruccion) # SentenciaWhile_Declaracion
			;
	*/
	public String visitSentenciaWhile_Declaracion(cRestringidoParser.SentenciaWhile_DeclaracionContext ctx) {
		System.out.println("while" + " (");
		visit(ctx.listaCondicion());
		System.out.println(") FIN Condicion del while");
		// Verificamos "(bloqueInstrucciones | tipoInstruccion)"
		if(ctx.bloqueInstrucciones() != null) {
			visit(ctx.bloqueInstrucciones());
		}
		else {
			visit(ctx.tipoInstruccion());
		}
		return "0";
	}

	/** sentenciaFor
			: 'for' '(' inicializacionFor? ';' listaCondicion? ';' alteracionFor? ')' (bloqueInstrucciones | tipoInstruccion) # SentenciaFor_Declaracion
			;
	*/
	public String visitSentenciaFor_Declaracion(cRestringidoParser.SentenciaFor_DeclaracionContext ctx) {
		System.out.println("for" + " (");
		// Verificamos "inicializacionFor?"
		if(ctx.inicializacionFor() != null) {
			visit(ctx.inicializacionFor());
		}
		// Verificamos "listaCondicion?"
		if(ctx.listaCondicion() != null) {
			visit(ctx.listaCondicion());
		}
		// Verificamos "alteracionFor?"
		if(ctx.alteracionFor() != null) {
			visit(ctx.alteracionFor());
		}
		System.out.println(") FIN for");
		// Verificamos "(bloqueInstrucciones | tipoInstruccion)"
		if(ctx.bloqueInstrucciones() != null) {
			visit(ctx.bloqueInstrucciones());
		}
		else {
			visit(ctx.tipoInstruccion());
		}
		return "0";
	}

	/** inicializacionFor
			: (declaracionVar | asignacionVar) (',' asignacionVar)* # InicializacionFor_ListaInicializacion
			;
	*/
	public String visitInicializacionFor_ListaInicializacion(cRestringidoParser.InicializacionFor_ListaInicializacionContext ctx) {
		System.out.println("ListaInicializacion" + " {");
		if(ctx.declaracionVar() != null) {
			visit(ctx.declaracionVar());
		}
		if(ctx.asignacionVar().size() > 0) {
			for(int i = 0; i < ctx.asignacionVar().size(); i++) {
				visit(ctx.asignacionVar(i));
			}
		}
		System.out.println("} FIN ListaInicializacion");
		return "0";
	}

	/** alteracionFor
			: asignacionVar (',' asignacionVar)* # AlteracionFor_ListaAlteracion
			;
	*/
	public String visitAlteracionFor_ListaAlteracion(cRestringidoParser.AlteracionFor_ListaAlteracionContext ctx) {
		System.out.println("ListaAlteracion" + " {");
		if(ctx.asignacionVar().size() > 0) {
			for(int i = 0; i < ctx.asignacionVar().size(); i++) {
				visit(ctx.asignacionVar(i));
			}
		}
		System.out.println("} FIN ListaAlteracion");
		return "0";
	}
}
