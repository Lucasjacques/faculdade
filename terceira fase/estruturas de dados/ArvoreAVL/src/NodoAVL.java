/*
 * Alunos: Lucas Jacques e Lucas Vieira
 * Implementa��o de uma �rvore AVL utilizando aloca��o din�mica de mem�ria.
 * 
 * Obs:
 * Foram encontrados muitos problemas na implementa��o relacionados a "Null Pointer Exception"
 * e "Stack Overflows" principalmente devido a grande utiliza��o de recurs�o. Os m�todos de rota��o
 * foram os mais dif�ceis de implementar, foi necess�rio buscar c�digos prontos para tentar entender
 * como deve ser feito. 
 * 
 * Principal fonte de pesquisa:
 * 	http://www.blackbam.at/blackbams-blog/2012/05/04/avl-tree-implementation-in-java/
 */

public class NodoAVL {
	
	public int codigo, fatorB;
	public NodoAVL pai, esquerda, direita;
	
	public NodoAVL(int codigo) {
		this.codigo = codigo;
		fatorB = 0;
		pai = null;
		esquerda = null;
		direita = null;
	}
	
}