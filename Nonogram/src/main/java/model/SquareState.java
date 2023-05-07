package model;


/*Each square during a game can be in 1 of 3 states:
It is unknown if the square is colored
It is known that square is colored
It is known that square is not colored
* */
public enum SquareState {
    UNKNOWN,
    COLORED,
    EMPTY
}
