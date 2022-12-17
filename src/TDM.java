import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TDM {
	private double[][] matrix; //Linha x Coluna
	private double[] vector;
	private boolean impossible = false;
	private boolean lindependent = false;

	private boolean linhaZero() {
		int zeros = 0;
		line:
		for(int i=0; i<vector.length; i++) {
			for(int j=0; j<vector.length; j++) {
				if(matrix[i][j] != 0) continue line;
			}
			zeros++;
		}
		if(zeros > 0) lindependent = true;
		return lindependent;
	}

	private boolean validador() {
		for(int i=0; i<vector.length; i++) {
			if(matrix[i][i] == 0) {
				int col = -1;
				for(int j=0; j<vector.length; j++) {
					if(matrix[j][i] != 0) {
						if(matrix[i][j] != 0) swapLine(i, j);
						else continue;
						col = 0;
					}
				}
				if(col == -1) {
					impossible = true;
					return false;
				}
			}
		}
		return true;
	}

	private void swapLine(int idx1, int idx2) {
		double tmpvec = vector[idx1];
		vector[idx1] = vector[idx2];
		vector[idx2] = tmpvec;

		double[] tmpmat = matrix[idx1];
		matrix[idx1] = matrix[idx2];
		matrix[idx2] = tmpmat;
	}

	private void lineConstDivide(int line, double div) {
		for(int i=0; i<vector.length; i++) matrix[line][i] /= div;
		vector[line] /= div;
	}

	private void lineSubtract(int line, int sub) {
		for(int i=0; i<vector.length; i++) matrix[line][i] -= matrix[sub][i];
		vector[line] -= vector[sub];
	}

    public void solve() {
        if (validador() && !linhaZero()) {
            for(int c = 0; c < vector.length; c++) {
				for(int l = c; l < vector.length; l++) {
					if(matrix[l][c] != 0 && matrix[l][c] != 1) lineConstDivide(l, matrix[l][c]);
				}
				for(int l = c+1; l < vector.length; l++) {
					if(matrix[l][c] != 0) lineSubtract(l, c);
				}
				if(linhaZero()) break;
			}
        }

        if(validador() && !lindependent) {
            for(int c=vector.length-1, h=1; c>=0; c--,h++) {
				for(int l = vector.length-h; l >= 0; l--) {
					if(matrix[l][c] != 0 && matrix[l][c] != 1) lineConstDivide(l, matrix[l][c]);
				}
				for(int l = vector.length-h-1; l >= 0; l--) {
					if(matrix[l][c] != 0) lineSubtract(l, c);
				}
				if(linhaZero()) break;
			}
        }

        if(lindependent) System.out.println("Matriz linearmente dependente");
        if(impossible) System.out.println("Matriz cont�m vari�vel imposs�vel");
    }

	public TDM(double[][] matrix, double[] vector) {
        this.matrix = matrix;
        this.vector = vector;
	}
}
