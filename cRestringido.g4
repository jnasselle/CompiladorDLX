/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar cRestringido;

cRestringido
    : declaracionFunc
    ;

declaracionFunc
    : TIPO_ID ID '(' parametrosFormales? ')' bloqueInstrucciones // int main() {...}
    ;

bloqueInstrucciones
    : '{' tipoInstruccion* '}' # BloqueInstrucciones_Bloque
    ;

tipoInstruccion
    : PUNTO_COMA                # TipoInstruccion_Punto_Coma
    | sentenciaIf               # TipoInstruccion_SentenciaIf
    | sentenciaFor              # TipoInstruccion_SentenciaFor
    | sentenciaWhile            # TipoInstruccion_SentenciaWhile
    | operacionMat PUNTO_COMA   # TipoInstruccion_OperacionMat
    | asignacionVar PUNTO_COMA  # TipoInstruccion_AsignacionVar
    | declaracionVar PUNTO_COMA # TipoInstruccion_DeclaracionVar
    ;

declaracionVar
    : TIPO_ID subDeclaracionVar (',' subDeclaracionVar)* # DeclaracionVar_Variables
    ;

subDeclaracionVar
	: ID ('=' tipoDeclaracionVar)? # SubDeclaracionVar_Id_Igual_TipoDeclVar
	;

tipoDeclaracionVar 
    : ID            # TipoDeclaracionVar_Id
    | NUMERO        # TipoDeclaracionVar_Numero
    | CARACTER      # TipoDeclaracionVar_Caracter 
    | operacionMat  # TipoDeclaracionVar_OperacionMat
    ;

asignacionVar
    : ID '=' operacionMat # AsignacionVar_Asignacion
    ;

sentenciaIf
    : 'if' '(' listaCondicion ')' (bloqueInstrucciones | tipoInstruccion) sentenciaElse? # SentenciaIf_Declaracion
    ;

sentenciaElse
    : 'else' sentenciaElse # SentenciaElse_Declaracion
    | bloqueInstrucciones  # SentenciaElse_BloqueInstrucciones
    | tipoInstruccion      # SentenciaElse_TipoInstruccion
    ;

sentenciaWhile
    : 'while' '(' listaCondicion ')' (bloqueInstrucciones | tipoInstruccion) # SentenciaWhile_Declaracion
    ;

sentenciaFor
    : 'for' '(' inicializacionFor? ';' listaCondicion? ';' alteracionFor? ')' (bloqueInstrucciones | tipoInstruccion) # SentenciaFor_Declaracion
    ;

inicializacionFor
    : (declaracionVar | asignacionVar) (',' asignacionVar)* # InicializacionFor_ListaInicializacion
    ;

alteracionFor
    : asignacionVar (',' asignacionVar)* # AlteracionFor_ListaAlteracion
    ;

listaCondicion
    : operacionCond (',' operacionCond)* # ListaCondicion_Lista
    ;

operacionCond
    : OPERADOR_NOT? ID                                 # OperacionCond_OpNot_Id
    | OPERADOR_NOT? NUMERO                             # OperacionCond_OpNot_Numero
    | OPERADOR_NOT? CARACTER                           # OperacionCond_OpNot_Caracter
    | OPERADOR_NOT? '(' operacionCond ')'              # OperacionCond_OpNot_Paren_OpCond_Paren
    | operacionCond OPERADOR_LOGICO operacionCond      # OperacionCond_OpCond_OpLogico_OpCond
    | operacionCond OPERADOR_COMPARACION operacionCond # OperacionCond_OpCond_OpComp_OpCond
    | operacionMat                                     # OperacionCond_OperacionMat
    ;

operacionMat
    : ID                                     # OperacionMat_Id
    | NUMERO                                 # OperacionMat_Numero
    | CARACTER                               # OperacionMat_Caracter
    | incrementoVar                          # OperacionMat_IncrementoVar
    | decrementoVar                          # OperacionMat_DecrementoVar
    | '(' operacionMat ')'                   # OperacionMat_Paren_OpMat_Paren
    | operacionMat OPERADOR_MAT operacionMat # OperacionMat_OpMat_Op_OpMat
    ;

incrementoVar
    : ID '+=' (CARACTER | NUMERO | ID) # IncrementoVar_SumaAsignacion
    | '++' ID                          # IncrementoVar_PreIncremento
    | ID '++'                          # IncrementoVar_PostIncremento
    ;

decrementoVar
    : ID '-=' (CARACTER | NUMERO | ID) # DecrementoVar_RestaAsignacion
    | '--' ID                          # DecrementoVar_PreDecremento
    | ID '--'                          # DecrementoVar_PostDecremento
    ;

parametrosFormales
    : TIPO_ID ID (',' TIPO_ID ID)*
    ;

CARACTER
    : '\'' LETRA? '\''
    ;

TIPO_ID
    : 'void'
    | 'int'
    | 'float'
    | 'char'
    ;

OPERADOR_MAT
    : ('*' | '/' | '%')
    | ('+' | '-')
    ;

OPERADOR_COMPARACION
    : ('==' | '!=')
    | ('<=' | '>=')
    | ('<' | '>')
    ;

OPERADOR_LOGICO
    : ('^') // XOR
    | ('&&' | '||')
    ;

OPERADOR_NOT
    : '!'
    ;

PUNTO_COMA
	: ';'
	;

ID
    : LETRA (LETRA | DIGITO)*
    ;

NUMERO
    : ENTERO
    | FLOTANTE
    ;

ENTERO
    : '-'? DIGITO+
    ;

FLOTANTE
    : ENTERO '.' DIGITO*
    | '.' DIGITO+
    ;

INCLUDE_01
    : '#' 'include' ' '? '<' .*? '>' -> skip
    ;

INCLUDE_02
    : '#' 'include' ' '? '"' .*? '"' -> skip
    ;

ESPACIO_BLANCO
    : [ \t\n\r]+ -> skip // saltar espacios, tabs, saltos de lineas, \r (Windows)
    ;

COMENTARIO_BLOQUE
    : '/*' .*? '*/' -> skip
    ;

COMENTARIO_LINEA
    : '//' .*? '\n' -> skip
    ;

PRINTF
    : 'printf' '(' .*? ')' ';' -> skip
    ;

SCANF
    : 'scanf' '(' .*? ')' ';' -> skip
    ;

SYSTEM
    : 'system' '(' .*? ')' ';' -> skip
    ;

RETURN
    : 'return' .*? ';' -> skip
    ;

fragment
    LETRA : 'a'..'z'
          | 'A'..'Z'
          | '_'
          ;

fragment
    DIGITO : '0'..'9'
           ;
