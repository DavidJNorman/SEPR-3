package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.rear_admirals.york_pirates.College;
import com.rear_admirals.york_pirates.ShipType;
import com.rear_admirals.york_pirates.screen.combat.CombatScreen;
import com.rear_admirals.york_pirates.base.BaseActor;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.base.BaseScreen;
import com.rear_admirals.york_pirates.Ship;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static com.rear_admirals.york_pirates.College.*;
import static com.rear_admirals.york_pirates.PirateGame.Chemistry;
import static com.rear_admirals.york_pirates.PirateGame.Physics;
import static com.rear_admirals.york_pirates.ShipType.*;

public class SailingScreen extends BaseScreen {

    private Ship playerShip;

    //Map Variables
    private ArrayList<BaseActor> obstacleList;
    private ArrayList<BaseActor> removeList;
    private ArrayList<BaseActor> regionList;

    private int tileSize = 64;
    private int tileCountWidth = 160;
    private int tileCountHeight = 80;

    //calculate game world dimensions
    private final int mapWidth = tileSize * tileCountWidth;
    private final int mapHeight = tileSize * tileCountHeight;
    private TiledMap tiledMap;

    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera tiledCamera;
    private int[] backgroundLayers = {0,1,2};
    private int[] foregroundLayers = {3};

    private Label pointsLabel;
    private Label goldLabel;
    private Label attackBuff;
    private Label accuracyBuff;
    private Label mapMessage;
    private Label hintMessage;


    private Float timer;
    private float messageDisplayTimer;
    private boolean unlockMessageDisplayed = false;

    public SailingScreen(final PirateGame main){
        super(main);

        playerShip = main.getPlayer().getPlayerShip();
        System.out.println(playerShip.getName());

        mainStage.addActor(playerShip);
        System.out.println("playerShip added");

        Table uiTable = new Table();

        Label pointsTextLabel = new Label("Points: ", main.getSkin(),"default_black");
        pointsLabel = new Label(Integer.toString(main.getPlayer().getPoints()), main.getSkin(), "default_black");
        pointsLabel.setAlignment(Align.left);

        Label goldTextLabel = new Label("Gold:", main.getSkin(),"default_black");
        goldLabel = new Label(Integer.toString(main.getPlayer().getGold()), main.getSkin(), "default_black");
        goldLabel.setAlignment(Align.left);

        Label attackBuffTextLabel = new Label("Attack Buff:",main.getSkin(),"default_black");
        attackBuff = new Label(Integer.toString(main.getPlayer().getAttackBuffTurns()),main.getSkin(),"default_black");
        attackBuff.setAlignment(Align.left);

        Label accuracyBuffTextLabel = new Label("Accuracy Buff", main.getSkin(),"default_black");
        accuracyBuff = new Label(Integer.toString(main.getPlayer().getAccuracyBuffTurns()),main.getSkin(),"default_black");
        accuracyBuff.setAlignment(Align.left);


        uiTable.add(pointsTextLabel).fill();
        uiTable.add(pointsLabel).width(pointsTextLabel.getWidth());
        uiTable.row();
        uiTable.add(goldTextLabel).fill();
        uiTable.add(goldLabel).fill();
        uiTable.row();
        uiTable.add(attackBuffTextLabel).fill();
        uiTable.add(attackBuff).fill();
        uiTable.row();
        uiTable.add(accuracyBuffTextLabel).fill();
        uiTable.add(accuracyBuff).fill();

        uiTable.align(Align.topRight);
        uiTable.setFillParent(true);

        uiStage.addActor(uiTable);

        mapMessage = new Label("", main.getSkin(), "default_black");
        hintMessage = new Label("", main.getSkin(),"default_black");

        Table messageTable = new Table();
        messageTable.add(mapMessage);
        messageTable.row();
        messageTable.add(hintMessage);

        messageTable.setFillParent(true);
        messageTable.top();

        uiStage.addActor(messageTable);

        obstacleList = new ArrayList<BaseActor>();
        removeList = new ArrayList<BaseActor>();
        regionList = new ArrayList<BaseActor>();

        // set up tile map, renderer and camera
        tiledMap = new TmxMapLoader().load("game_map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false, viewwidth, viewheight);
        tiledCamera.update();


        createObjectData();
        createPhysicData();
        createRegionData();



        timer = 0f;

        InputMultiplexer im = new InputMultiplexer(uiStage, mainStage);
        Gdx.input.setInputProcessor(im);

        // Debug processor
//        System.out.println("IP: im");
    }

    @Override
    public void update(float delta) {
        removeList.clear();
        goldLabel.setText(Integer.toString(pirateGame.getPlayer().getGold()));
        this.playerShip.playerMove(delta);

        Boolean x = false;
        for (BaseActor region : regionList) {
            String name = region.getName();
            if (playerShip.overlaps(region, false)) {
                x = true;
                mapMessage.setText(capitalizeFirstLetter(name.substring(0, name.length() - 6)) + " Territory");
                int enemyChance = ThreadLocalRandom.current().nextInt(0, 10001);
                if (enemyChance <= 10) {
                    System.out.println("Enemy Found in " + name);
                    College college = region.getCollege();                              //College object
                    Gdx.app.log("Name of Region", name);
                    if (!playerShip.getCollege().getAlly().contains(college)) {
                        System.out.println(name);
                        pirateGame.setScreen(new CombatScreen(pirateGame, new Ship(Brig, college)));
                    }
                }
            }

        }

        if (!x) {
            mapMessage.setText("Neutral Territory");
            //Spawn sea monsters while in neutral territory
            //rarer than enemy ships in college regions
            int monsterChance = ThreadLocalRandom.current().nextInt(0, 20000);
            if (monsterChance <= 10) {
                System.out.println("Sea Monster attack!");
                pirateGame.setScreen(new CombatScreen(pirateGame, new Ship(SeaMonster, Sea)));
            }
        }





        Boolean y = false;
        for (BaseActor obstacle : obstacleList) {
            String name = obstacle.getName();
            //add airwall to prevent player go to new area before defeat james and vanbrugh

            if(name.equals("airwall")&&playerShip.getCollege().getAlly().size() >= 3){
                playerShip.overlaps(obstacle, false);
            }
            else if (playerShip.overlaps(obstacle, true)) {
                y = true;
                if(name.equals("airwall")&&playerShip.getCollege().getAlly().size() < 3){
                    mapMessage.setText("Defeat James and Vanbrugh to Unlock");
                }
                if (!(obstacle.getDepartment() == null)) {
                    mapMessage.setText(capitalizeFirstLetter(name) + " Island");
                    hintMessage.setText("Press F to interact");
                    if (Gdx.input.isKeyPressed(Input.Keys.F)) pirateGame.setScreen(new DepartmentScreen(pirateGame, obstacle.getDepartment()));
                }
                // Obstacle must be a college if college not null
                else if (!(obstacle.getCollege() == null)) {
                    mapMessage.setText(capitalizeFirstLetter(name) + " Island");
                    hintMessage.setText("Press F to interact");
//                    System.out.println("A college");
                    College college = obstacle.getCollege();
                    if (Gdx.input.isKeyPressed(Input.Keys.F)) {
                        System.out.println("A college");
                        if (!playerShip.getCollege().getAlly().contains(college) && obstacle.getCollege().isBossDead() == false) {  //If enemy ship is not an ally and the boss is still alive, go into battle with boss?
                            System.out.println("Enemy");


                            ////
                            Gdx.app.log("CollegeName", obstacle.getName());
                            ShipType BattleShip = CollegeChecker(obstacle.getName());
                            pirateGame.setScreen(new CombatScreen(pirateGame, new Ship(BattleShip, college, college.getName() + " Boss", true)));
                            ////


                        } else {
                            System.out.println("Ally");
                            pirateGame.setScreen(new CollegeScreen(pirateGame, college));
                        }
                    }
                } else {
//                    System.out.println("Pure obstacle");
                }
            }
        }

        if (!y) hintMessage.setText("");

        for (BaseActor object : removeList) {
            object.remove();
        }

        // camera adjustment
        Camera mainCamera = mainStage.getCamera();

        // center camera on player
        mainCamera.position.x = playerShip.getX() + playerShip.getOriginX();
        mainCamera.position.y = playerShip.getY() + playerShip.getOriginY();

        // bound camera to layout
        mainCamera.position.x = MathUtils.clamp(mainCamera.position.x, viewwidth / 2, mapWidth - viewwidth / 2);
        mainCamera.position.y = MathUtils.clamp(mainCamera.position.y, viewheight / 2, mapHeight - viewheight / 2);
        mainCamera.update();

        // adjust tilemap camera to stay in sync with main camera
        tiledCamera.position.x = mainCamera.position.x;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);

        timer += delta;
        if (timer > 1) {
            pirateGame.getPlayer().addPoints(1);
            timer -= 1;
        }
        pointsLabel.setText(Integer.toString(pirateGame.getPlayer().getPoints()));
        attackBuff.setText(Integer.toString(pirateGame.getPlayer().getAttackBuffTurns()));
        accuracyBuff.setText(Integer.toString(pirateGame.getPlayer().getAccuracyBuffTurns()));

        //new area unlocked message
        if(playerShip.getCollege().getAlly().contains(James)
                && playerShip.getCollege().getAlly().contains(Vanbrugh)
                && !unlockMessageDisplayed){
            mapMessage.setText("New Area Unlocked");
            //display time counter start
            messageDisplayTimer += delta;
            if(messageDisplayTimer > 3) {
                unlockMessageDisplayed = true;
                messageDisplayTimer = 0;
            }
        }
    }

    public ShipType CollegeChecker(String name){
        switch (name){
            case "derwent":
               Gdx.app.log("College Name", DerwentCollege.getName());
                return DerwentCollege;
            case "vanbrugh":
                Gdx.app.log("College Name", VanbrughCollege.getName());
                return VanbrughCollege;
            case "james":
                Gdx.app.log("College Name", JamesCollege.getName());
                return JamesCollege;
            case "langwith":
                Gdx.app.log("College Name", LangwithCollege.getName());
                return LangwithCollege;
            case "goodricke":
                Gdx.app.log("College Name", GoodrickeCollege.getName());
                return GoodrickeCollege;
        }
        return Brig;
    }


    @Override
    public void render(float delta) {
        uiStage.act(delta);

        mainStage.act(delta);
        update(delta);

        // render
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tiledMapRenderer.render(backgroundLayers);
        mainStage.draw();

        tiledMapRenderer.render(foregroundLayers);

        uiStage.draw();

        if (!playerShip.isAnchor()){
            playerShip.addAccelerationAS(playerShip.getRotation(), 10000);
        } else{
            playerShip.setAccelerationXY(0,0);
            playerShip.setDeceleration(100);
        }
    }

    @Override
    public void dispose () {
        mainStage.dispose();
        uiStage.dispose();
        playerShip.getSailingTexture().dispose();
    }

    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }

    //create region information
    private void createRegionData(){
        MapObjects objects = tiledMap.getLayers().get("RegionData").getObjects();
        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
//                RectangleMapObject rectangleObject = (RectangleMapObject) object;
//                Rectangle r = rectangleObject.getRectangle();
//
//                BaseActor region = new BaseActor();
//                region.setPosition(r.x, r.y);
//                region.setSize(r.width, r.height);
                BaseActor region = setUpBaseActor(object);
                region.setRectangleBoundary();
                region.setName(object.getName());

                String cases = object.getName();
//                System.out.println(cases);

                switch (cases){
                    case "derwentregion":
                        region.setCollege(Derwent);
                        break;
                    case "jamesregion":
                        region.setCollege(James);
                        break;
                    case "vanbrughregion" :
                        region.setCollege(Vanbrugh);
                        break;
                    case "goodrickeregion":
                        region.setCollege(Goodricke);
                        break;
                    case "langwithregion" :
                        region.setCollege(Langwith);
                }

//                if (object.getName().equals("derwentregion")) region.setCollege(Derwent);
//                else if (object.getName().equals("jamesregion")) region.setCollege(James);
//                else if (object.getName().equals("vanbrughregion")) region.setCollege(Vanbrugh);
//                else if (object.getName().equals("goodrickeregion")) region.setCollege(Goodricke);
//                else if (object.getName().equals("langwithregion")) region.setCollege(Langwith);
                regionList.add(region);
            } else {
                System.err.println("Unknown RegionData object.");
            }
        }
    }

    //create physic collision information for bound and island
    private void createPhysicData(){
        MapObjects objects = tiledMap.getLayers().get("PhysicsData").getObjects();
        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
//                RectangleMapObject rectangleObject = (RectangleMapObject) object;
//                Rectangle r = rectangleObject.getRectangle();
//
//                BaseActor solid = new BaseActor();
//                solid.setPosition(r.x, r.y);
//                solid.setSize(r.width, r.height);
                BaseActor solid = setUpBaseActor(object);
                solid.setName(object.getName());
                solid.setRectangleBoundary();
                String objectName = object.getName();

                if (objectName.equals("derwent")) solid.setCollege(Derwent);
                else if (objectName.equals("james")) solid.setCollege(James);
                else if (objectName.equals("vanbrugh")) solid.setCollege(Vanbrugh);
                else if (objectName.equals("chemistry"))solid.setDepartment(Chemistry);
                else if (objectName.equals("physics")) solid.setDepartment(Physics);
                else if (objectName.equals("goodricke")) solid.setCollege(Goodricke);
                else if (objectName.equals("langwith")) solid.setCollege(Langwith);
                else{
                    System.out.println("Not college/department: " + solid.getName());
                }
                obstacleList.add(solid);
            } else {
                System.err.println("Unknown PhysicsData object.");
            }
        }
    }

    //create physic collision information for player and objects(currently null)
    private void createObjectData(){
        MapObjects objects = tiledMap.getLayers().get("ObjectData").getObjects();
        for (MapObject object : objects) {
            String name = object.getName();

            // all object data assumed to be stored as rectangles
            RectangleMapObject rectangleObject = (RectangleMapObject)object;
            Rectangle r = rectangleObject.getRectangle();

            if (name.equals("player")){
                playerShip.setPosition(r.x, r.y);
            } else{
                System.err.println("Unknown tilemap object: " + name);
            }
        }

    }

    //create BaseActor used in createXXXData() functions
    private BaseActor setUpBaseActor(MapObject object){
        RectangleMapObject rectangleObject = (RectangleMapObject) object;
        Rectangle r = rectangleObject.getRectangle();

        BaseActor baseActor = new BaseActor();
        baseActor.setPosition(r.x, r.y);
        baseActor.setSize(r.width, r.height);
        return baseActor;

    }
}
