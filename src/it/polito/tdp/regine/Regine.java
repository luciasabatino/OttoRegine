package it.polito.tdp.regine;

import java.util.ArrayList;
import java.util.List;

/**
 * La disposizione di un certo numero di regine sulla scacchiera
 * 
 * @author Fulvio
 *
 */
public class Regine {
	
	public List<Integer> posiziona(int N){
		List<Integer> soluzione = new ArrayList<Integer>();
		if(cerca(soluzione,N,0))
			return soluzione;
		else
			return null;
	}
	
	/**
	 * Assumendo che scacchiera sia piena nelle righe da 0 a livello-1,
	 * determina le possibili posizioni per una regina alla riga==livello
	 * e prova ricorsivamente a posizionarla
	 * 
	 * @param scacchiera lista delle posizioni (colonna) delle regine precedenti
	 * @param N dimensione scacchiera
	 * @param livello riga a cui deve lavorare la ricorsione
	 */
	private boolean cerca(List<Integer> scacchiera, int N, int livello) {
		if(livello==N) {
			//caso terminale, soluzione trovata
			return true;
		}
		for(int mossa=0; mossa<N;mossa++) {
			//Verifico che quella casella di mossa non vada in conflitto con le regine precedenti
			if(posizioneSicura(scacchiera,livello,N,mossa)) {
			scacchiera.add(mossa);
			if(cerca(scacchiera,N,livello+1))
				return true;
			scacchiera.remove(livello); //backtrack
			}
		}
		return false;
	}

	private boolean posizioneSicura(List<Integer> scacchiera, int livello, int n, int mossa) {
		for(int riga=0; riga<livello; riga++) {
			//verifica delle colonne
			if(scacchiera.get(riga)==mossa)
				return false;
			
			//verifica diagonale destra: R1-C1 ==R2-C2
			if(riga-scacchiera.get(riga)==livello-mossa)
				return false;
				
			//verifica diagonale sinistra: R1+C1 = R2+C2
			if(riga+scacchiera.get(riga)==livello+mossa)
				return false;
		}
		
		return true;
	}

}
