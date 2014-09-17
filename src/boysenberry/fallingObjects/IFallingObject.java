package boysenberry.fallingObjects;

import boysenberry.IGameObject;

public interface IFallingObject extends IGameObject {
    public void registerCollision();
    public void update();
}
