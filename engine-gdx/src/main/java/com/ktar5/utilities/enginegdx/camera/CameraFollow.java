package com.ktar5.utilities.enginegdx.camera;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ktar5.utilities.enginegdx.EngineManager;
import com.ktar5.utilities.enginegdx.entity.components.Position;
import com.ktar5.utilities.enginegdx.rendering.Renderable;
import com.ktar5.utilities.libgdx.Feature;
import com.ktar5.utilities.libgdx.camera.CameraBase;
import lombok.Setter;

@Setter
public class CameraFollow extends CameraBase implements Renderable {
    private Position position;

    public CameraFollow(OrthographicCamera camera, Viewport viewport, Position position) {
        super(camera, viewport);
        this.position = position;
    }

    CameraType type = CameraType.MOUSE;

    enum CameraType {
        FORWARD,
        BACKWARD,
        DIRECT,
        MOUSE
    }

    //Temporary variables
    private Vector2 vec2 = new Vector2(0, 0);
    private Vector3 vec3 = new Vector3(0, 0, 0);

    private float previousAngle;
    private float alpha;

    @Override
    public void update(float dTime) {
        if (Feature.CAMERA_MOVEMENT.isDisabled()) {
            return;
        }

//        PlayerEntity playerEntity = EngineManager.get().getPlayer();
//        switch (type) {
//            case FORWARD: {
//                float angle = Math.abs(previousAngle - playerEntity.getMovement().getVelocity().angle());
//                if (playerEntity.getMovement().getVelocity().len2() > .01f) {
//                    previousAngle = playerEntity.getMovement().getVelocity().angle();
//                }
//
//                System.out.print("Angle: " + playerEntity.getMovement().getVelocity().angle());
//                System.out.print(" Length^2: " + playerEntity.getMovement().getVelocity().len2());
//                System.out.print(" X: " + playerEntity.getMovement().getVelocity().x);
//                System.out.print(" Y: " + playerEntity.getMovement().getVelocity().y);
//                System.out.print(" PrevAngle: " + previousAngle);
//                System.out.println(" CamPost: " + camera.position.x);
//                System.out.println();
//                //System.out.println(playerEntity.getMovement().getAccelX() + playerEntity.getMovement().getAccelY());
//                System.out.println();
//
//                /*float accel = Math.abs(playerEntity.getMovement().getAccelX()) + Math.abs(playerEntity.getMovement().getAccelY());
//                accel = deadzone(accel, .01f);
//                if (accel > 0) {
//                    alpha += CAMERA_SMOOTH * .5;
//                } else if (accel == 0 && playerEntity.getVelocity().len2() >= 300) {
//                    alpha += CAMERA_SMOOTH * .5;
//                } else if (accel == 0 && playerEntity.getVelocity().len2() < 300) {
//                    alpha -= CAMERA_SMOOTH * .2;
//                }*/
//
//                float sf = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) ? 1.4f : 1f;
//
//                alpha = MathUtils.clamp(alpha, 0, 1f * sf);
//                vec2.set(6 * sf, 6 * sf).setAngle(previousAngle);
//                vec2 = playerEntity.getRawPosition().cpy().add(vec2);
//                vec3.set(playerEntity.getRawPosition().cpy().lerp(vec2, alpha / sf), 0);
//                //vec3 = camera.position.cpy().slerp(vec3, .2f);
//                break;
//            }
//            case BACKWARD:
//                //Set vec2 to Player position
//                vec2 = playerEntity.getRawPosition().cpy();
//                //Lerp camera towards player
//                vec3 = camera.position.cpy().slerp(vec3.set(vec2, 0), CAMERA_SMOOTH);
//                break;
//            case DIRECT:
//                //Set to Player position
//                vec3 = vec3.set(playerEntity.getRawPosition(), 0);
//                break;
//            case MOUSE: {
//                vec3 = vec3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//                vec3 = this.viewport.unproject(vec3);
//                float length = playerEntity.getRawPosition().dst(vec2.set(vec3.x, vec3.y));
//                vec2.set(playerEntity.position.x, playerEntity.position.y);
//                float angle = (float) Math.toDegrees(Math.atan2(vec2.x - vec3.x, vec3.y - vec2.y)) + 90;
//                vec2.set(2, 2).setAngle(angle);
//                vec2.add(playerEntity.getRawPosition());
//                //First alpha
//                this.alpha = MathUtils.clamp((length / 30f) - .1f, 0, 1f);
//                //Second alpha
//                this.alpha = Interpolation.fade.apply(alpha);
//                vec3.set(playerEntity.getRawPosition().cpy().lerp(vec2, this.alpha), 0);
//                break;
//            }
//        }
//
//        //PixelGridUtil.fixToResolution(vec3);
//
//        //Set camera position to fixed vector
//        camera.position.set(vec3);
//        //Update camera
//        camera.update();
    }

    public Vector3 boundToPlayer(Vector3 camPos, Vector2 playPos, float xDistance, float yDistance) {
        Vector2 v1 = playPos.cpy().add(xDistance, yDistance);
        Vector2 v2 = playPos.cpy().sub(xDistance, yDistance);
        if (camPos.x > v1.x) {
            camPos.x = v1.x;
        } else if (camPos.x < v2.x) {
            camPos.x = v2.x;
        }
        if (camPos.y > v1.y) {
            camPos.y = v1.y;
        } else if (camPos.y < v2.y) {
            camPos.y = v2.y;
        }
        return camPos;
    }

    public boolean withinBounds(Vector3 camPos, Vector2 playPos, float xDistance, float yDistance) {
        Vector2 v1 = playPos.cpy().add(xDistance, yDistance);
        Vector2 v2 = playPos.cpy().sub(xDistance, yDistance);
        return (camPos.x < v1.x && camPos.x > v2.x && camPos.y < v1.y && camPos.y > v2.y);
    }

    @Override
    public void render(SpriteBatch batch, float dTime) {

    }

    public void debug(float dTime) {
        Renderable.drawLine(EngineManager.get().getPlayer().getPosition(), vec2, 3, Color.DARK_GRAY);
        Renderable.drawSquare(camera.position.x, camera.position.y, .5f, .5f, Color.CYAN);
    }

}
