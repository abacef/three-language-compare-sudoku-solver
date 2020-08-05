// use regex::Regex;
use std::{fmt/*, env*/};
// use std::fs;
// use bit_set::BitSet;
use std::fmt::{Formatter};
use std::time::Instant;
use std::ops::Div;

// const ROW_SIZE: usize = 9;
// const NUMBERS_IN_A_BOARD: usize = ROW_SIZE * ROW_SIZE;
// const BOARD_INPUT_REGEX: &str = "^[0-9]{81}$";

/// If you want to have a user entered board, remove everything in main except the first commented
/// line, then uncomment everything that is commented in the file

struct Board {
    orij: Vec<Vec<u8>>,
    board: Vec<Vec<u8>>,
    row: usize,
    col: usize,
}

impl Board {

    // pub fn new_construct_board(input: String, row_size: usize) -> Board {
    //     let mut iter = input.chars().map(|c| c.to_digit(10).unwrap());
    //     let mut board = vec![vec![0; row_size]; row_size];
    //     for i in 0..row_size {
    //         for j in 0..row_size {
    //             board[i][j] = iter.next().unwrap() as u8
    //         }
    //     }
    //     let copy = board.clone();
    //
    //     Board {
    //         orij: board,
    //         board: copy,
    //         row: 0,
    //         col: 0,
    //     }
    // }

    pub fn new_inject_board(board: Vec<Vec<u8>>) -> Board {
        let copy = board.clone();
        Board {
            orij: board,
            board: copy,
            row: 0,
            col: 0,
        }
    }

    pub fn advance(&mut self) {
        if self.col == 8 {
            self.col = 0;
            self.row += 1;
        }
        else {
            self.col += 1;
        }
        if !(self.row == 9 && self.col == 0) {
            if self.orij[self.row][self.col] != 0 {
                self.advance();
            }
        }
    }

    pub fn deadvance(&mut self) {
        match self.col {
            0 => {
                if self.row != 0 {
                    self.col = 8;
                    self.row -= 1;
                }
            }
            _ => {
                self.col -= 1;
            }
        }

        if !(self.col == 0 && self.row == 0) {
            if self.orij[self.row][self.col] != 0  {
                self.deadvance();
            }
        }
    }

    pub fn check_square(&self) -> Result<bool, ()> {
        // get top left of box
        let r = match self.row {
            0 ..= 2 => 0,
            3 ..= 5 => 3,
            6 ..= 8 => 6,
            _ => return Err(())
        };

        let c = match self.col {
            0 ..= 2 => 0,
            3 ..= 5 => 3,
            6 ..= 8 => 6,
            _ => return Err(())
        };

        let num = self.board[self.row][self.col];
        for i in 0 .. 3 {
            for j in 0 .. 3 {
                if ((i + r) != self.row || (j + c) != self.col) &&
                    self.board[i + r][j + c] == num {
                    return Ok(false)
                }
            }
        }
        Ok(true)
    }

    pub fn is_valid(&self) -> bool {
        let num = self.board[self.row][self.col];
        for i in 0 .. 9 {
            if i != self.col && self.board[self.row][i] == num {
                return false
            }
            if i != self.row && self.board[i][self.col] == num {
                return false
            }
        }
        self.check_square().unwrap()
    }

    pub fn solve(&mut self) -> Option<Instant> {
        if self.row == 9 && self.col == 0 {
            return Option::Some(Instant::now());
        }
        for i in 1 .. 10 {
            self.board[self.row][self.col] = i;
            if self.is_valid() {
                self.advance();
                let end = self.solve();
                if end.is_some() {
                    return end;
                }
            }
        }
        self.board[self.row][self.col] = 0;
        self.deadvance();
        Option::None
    }
}

impl fmt::Display for Board {
    fn fmt(&self, f: &mut Formatter<'_>) -> fmt::Result {
        self.board.iter().for_each(|x| {
            x.iter().for_each(|y| write!(f, "{} ", y).unwrap());
            writeln!(f, "").unwrap();
        });
        Ok(())
    }
}

// /// Parses a file with 81 characters that represent the board. Zeros represent unknown numbers
// fn parse_args(args: Vec<String>) -> Board {
//     if args.len() != 2 {
//         panic!(format!("Must include only one argument: the {} character game board", NUMBERS_IN_A_BOARD));
//     }
//     let board_file = &args[1];
//     let input = fs::read_to_string(board_file).unwrap();
//
//     let re = Regex::new(BOARD_INPUT_REGEX).unwrap();
//     if !re.is_match(&input) {
//         panic!("Board input formatted incorrectly");
//     }
//
//     Board::new(input, ROW_SIZE)
// }


fn main() {
    // let mut board = parse_args(env::args().collect());

    let mut micro_counter: u128 = 0;

    let boards =  vec!(
        vec!(
            vec!(0, 0, 7, 0, 5, 0, 0, 9, 3),
            vec!(5, 8, 0, 7, 0, 0, 1, 6, 0),
            vec!(9, 0, 6, 8, 0, 3, 0, 2, 0),
            vec!(1, 0, 0, 0, 0, 0, 6, 0, 0),
            vec!(8, 6, 0, 0, 0, 0, 0, 7, 2),
            vec!(0, 0, 2, 0, 0, 0, 0, 0, 1),
            vec!(0, 5, 0, 3, 0, 9, 7, 0, 8),
            vec!(0, 2, 8, 0, 0, 4, 0, 1, 9),
            vec!(3, 9, 0, 0, 7, 0, 2, 0, 0)
        ),
        vec!(
            vec!(0, 0, 4, 0, 3, 0, 8, 0, 0),
            vec!(0, 0, 0, 0, 0, 4, 5, 0, 0),
            vec!(2, 6, 0, 8, 0, 0, 3, 0, 0),
            vec!(4, 0, 0, 0, 7, 9, 0, 0, 6),
            vec!(3, 0, 0, 1, 4, 8, 0, 0, 7),
            vec!(7, 0, 0, 3, 6, 0, 0, 0, 8),
            vec!(0, 0, 9, 0, 0, 3, 0, 8, 5),
            vec!(0, 0, 1, 6, 0, 0, 0, 0, 0),
            vec!(0, 0, 3, 0, 2, 0, 6, 0, 0)
        ),
        vec!(
            vec!(0, 0, 1, 0, 0, 0, 0, 2, 4),
            vec!(7, 0, 0, 6, 1, 0, 0, 0, 0),
            vec!(0, 0, 0, 0, 0, 9, 0, 0, 8),
            vec!(6, 0, 0, 0, 0, 0, 2, 8, 1),
            vec!(0, 4, 0, 0, 5, 0, 0, 3, 0),
            vec!(1, 7, 3, 0, 0, 0, 0, 0, 6),
            vec!(5, 0, 0, 3, 0, 0, 0, 0, 0),
            vec!(0, 0, 0, 0, 4, 5, 0, 0, 2),
            vec!(4, 6, 0, 0, 0, 0, 5, 0, 0)
        ),
        vec!(
            vec!(0, 2, 0, 7, 0, 0, 3, 0, 0),
            vec!(5, 0, 0, 0, 0, 4, 0, 6, 0),
            vec!(0, 0, 6, 1, 3, 0, 0, 0, 0),
            vec!(0, 0, 2, 0, 6, 0, 0, 0, 4),
            vec!(8, 0, 0, 0, 0, 0, 0, 0, 5),
            vec!(4, 0, 0, 0, 1, 0, 8, 0, 0),
            vec!(0, 0, 0, 0, 8, 7, 1, 0, 0),
            vec!(0, 8, 0, 5, 0, 0, 0, 0, 7),
            vec!(0, 0, 7, 0, 0, 2, 0, 9, 0)
        )
    );

    for game_board in boards {
        let mut board = Board::new_inject_board(game_board);

        let start = Instant::now();
        let end = board.solve().unwrap();

        print!("{}", board);

        let duration = end.duration_since(start);
        micro_counter += duration.as_micros();
        println!("{:?} milliseconds\n", (duration.as_micros() as f64).div(1000 as f64));
    }
    println!("{} milliseconds total", (micro_counter as f64).div(1000 as f64));
}
