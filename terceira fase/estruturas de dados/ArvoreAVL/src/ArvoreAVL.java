public class ArvoreAVL {

	private NodoAVL raiz;

	public ArvoreAVL() {
		raiz = null;
	}
	
	public boolean busca(int buscado) {
		return busca(raiz, buscado);
	}
	
	private boolean busca(NodoAVL nodo, int buscado) {
		if(nodo == null) {
			return false;
		}
		if(nodo.dado == buscado) {
			return true;
		} else if(nodo.dado > buscado) {
			return busca(nodo.esquerda, buscado);
		} else {
			return busca(nodo.direita, buscado);
		}
	}

	public void inserir(int novo) {
		inserir(raiz, new NodoAVL(novo));
	}

	private void inserir(NodoAVL pai, NodoAVL novo) {
		if (pai == null) {
			raiz = novo;
		} else {
			if (novo.dado > pai.dado) {
				if (pai.direita == null) {
					pai.direita = novo;
					novo.pai = pai;
					balanceia(pai);
				} else {
					inserir(pai.direita, novo);
				}
			} else if (novo.dado < pai.dado) {
				if (pai.esquerda == null) {
					pai.esquerda = novo;
					novo.pai = pai;
					balanceia(pai);
				} else {
					inserir(pai.esquerda, novo);
				}
			}
		}
	}

	private void balanceia(NodoAVL atual) {
		if (atual == null) {
			return;
		}
		atual.fatorB = calculaFatorB(atual);
		if (atual.fatorB == -2) {
			if(atual.direita.fatorB <= 0) {
				if (atual == raiz) {
					raiz = rotacaoEsquerda(atual);
				} else {
					atual = rotacaoEsquerda(atual);
				}
			}else {
				atual = rotacaoDireitaEsquerda(atual);
			}
		} else if (atual.fatorB == 2) {
			if (atual.esquerda.fatorB >= 0) {
				if (atual == raiz) {
					raiz = rotacaoDireita(atual);
				} else {
					atual = rotacaoDireita(atual);
				}
			} else {
				atual = rotacaoEsquerdaDireita(atual);
			}
		} else {
			balanceia(atual.pai);
		}
	}

	private NodoAVL rotacaoDireita(NodoAVL raizVelha) {
		NodoAVL novaRaiz = raizVelha.esquerda;
		novaRaiz.pai = raizVelha.pai;
		raizVelha.esquerda = novaRaiz.direita;
		if (raizVelha.esquerda != null) {
			raizVelha.esquerda.pai = raizVelha;
		}
		novaRaiz.direita = raizVelha;
		raizVelha.pai = novaRaiz;
		if (novaRaiz.pai != null) {
			if (novaRaiz.pai.direita == raizVelha) {
				novaRaiz.pai.direita = novaRaiz;
			} else if (novaRaiz.pai.esquerda == raizVelha) {
				novaRaiz.pai.esquerda = novaRaiz;
			}
		}
		raizVelha.fatorB = calculaFatorB(raizVelha);
		novaRaiz.fatorB = calculaFatorB(novaRaiz);
		return novaRaiz;
	}

	private NodoAVL rotacaoEsquerda(NodoAVL raizVelha) {
		NodoAVL novaRaiz = raizVelha.direita;
		novaRaiz.pai = raizVelha.pai;
		raizVelha.direita = novaRaiz.esquerda;
		if (raizVelha.direita != null) {
			raizVelha.direita.pai = raizVelha;
		}
		novaRaiz.esquerda = raizVelha;
		raizVelha.pai = novaRaiz;
		if (novaRaiz.pai != null) {
			if (novaRaiz.pai.esquerda == raizVelha) {
				novaRaiz.pai.esquerda = novaRaiz;
			} else if (novaRaiz.pai.direita == raizVelha) {
				novaRaiz.pai.direita = novaRaiz;
			}
		}
		raizVelha.fatorB = calculaFatorB(raizVelha);
		novaRaiz.fatorB = calculaFatorB(novaRaiz);
		return novaRaiz;
	}

	private NodoAVL rotacaoEsquerdaDireita(NodoAVL raizVelha) {
		raizVelha.esquerda = rotacaoEsquerda(raizVelha.esquerda);
		return rotacaoDireita(raizVelha);
	}

	private NodoAVL rotacaoDireitaEsquerda(NodoAVL raizVelha) {
		raizVelha.direita = rotacaoDireita(raizVelha.direita);
		return rotacaoEsquerda(raizVelha);
	}

	private int calculaAltura(NodoAVL nodo) {
		if (nodo == null) {
			return -1;
		}
		if (nodo.esquerda == null && nodo.direita == null) {
			return 0;
		} else if (nodo.esquerda == null) {
			return 1 + calculaAltura(nodo.direita);
		} else if (nodo.direita == null) {
			return 1 + calculaAltura(nodo.esquerda);
		} else {
			return 1 + Math.max(calculaAltura(nodo.esquerda),
					calculaAltura(nodo.direita));
		}
	}

	private int calculaFatorB(NodoAVL nodo) {
		int alturaEsquerda = calculaAltura(nodo.esquerda);
		int alturaDireita = calculaAltura(nodo.direita);
		return alturaEsquerda - alturaDireita;
	}

	public void printPreOrder() {
		printPreOrder(raiz);
	}

	private void printPreOrder(NodoAVL nodo) {
		if (nodo != null) {
			System.out.println(nodo.dado);
			printPreOrder(nodo.esquerda);
			printPreOrder(nodo.direita);
		}
	}

	public static void main(String[] args) {
		ArvoreAVL avl = new ArvoreAVL();
		for(int i = 0; i < 100000; i++) {
			System.out.println(i);
			avl.inserir( (int) (Math.random() * 1000000));
		}
		avl.printPreOrder();
		System.out.println(avl.busca(40));
	}

}