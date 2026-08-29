class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        //find empty cell
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    //try digits 0 to 9
                    for (char digit = '1'; digit <= '9'; digit++) {
                        if (isSafe(board, row, col, digit)) {
                            //place digit
                            board[row][col] = digit;
                            //solve remaining board
                            if (solve(board)) {
                                return true;
                            }
                            //backtrack
                            board[row][col] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private    boolean isSafe(char[][] board, int row, int col, char digit) {
        //check row
        for(int j=0; j<9; j++) {
            if(board[row][j] == digit) {
                return false;
            }
        }

        //chek col
        for(int i=0; i<9; i++) {
            if(board[i][col] == digit) {
                return false;
            }
        }
        
        //check 3 x 3
    int startRow  = (row/3)*3;
    int startCol = (col/3)*3;

    for(int i=startRow; i<startRow+3; i++) {
        for(int j=startCol; j<startCol+3; j++) {
            if(board[i][j] == digit) {
                return false;
            }
        }
    }
    return true;


    }
}