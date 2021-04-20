/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometry;


/**
 *
 * @author User
 */
public class Direction {
    Enum_Direction d;
    public Direction(Enum_Direction d) {
        this.d = d;
    }
    public Direction() {
        int d = (int)Math.floor(Math.random()*4);
        switch (d) {
            case 0:
                this.d = Enum_Direction.UP;
                break;
            case 1:
                this.d = Enum_Direction.RIGHT;
                break;
            case 2:
                this.d = Enum_Direction.DOWN;
                break;
            case 3:
                this.d = Enum_Direction.LEFT;
                break;
            default:
                break;
        }
    }
    public boolean isUp() {
        return this.d == Enum_Direction.UP;
    }
    public boolean isRight() {
        return this.d == Enum_Direction.RIGHT;
    }
    public boolean isDown() {
        return this.d == Enum_Direction.DOWN;
    }
    public boolean isLeft() {
        return this.d == Enum_Direction.LEFT;
    }
    public void rotate() {
        if (this.isUp()) this.d = Enum_Direction.RIGHT;
        else if (this.isRight()) this.d = Enum_Direction.DOWN;
        else if (this.isDown()) this.d = Enum_Direction.LEFT;
        else if (this.isLeft()) this.d = Enum_Direction.UP;
    }
    public Direction copy() {
        return new Direction(this.d);
    }
}
