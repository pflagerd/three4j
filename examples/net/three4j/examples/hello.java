package net.three4j.examples;

import static net.three4j.THREE.window;

import net.three4j.cameras.PerspectiveCamera;
import net.three4j.core.Geometry;
import net.three4j.geometries.BoxGeometry;
import net.three4j.materials.Material;
import net.three4j.materials.MeshNormalMaterial;
import net.three4j.objects.Mesh;
import net.three4j.renderers.WebGLRenderer;
import net.three4j.scenes.Scene;

class hello {

	PerspectiveCamera camera;
	Scene scene;
	WebGLRenderer renderer;
	Geometry geometry;
    Material material;
    Mesh mesh;

    public hello() {
        camera = new PerspectiveCamera( 70, (double)window.innerWidth / window.innerHeight, 0.01, 10 );
        camera.position().z(1);
//        println(jsonify(camera.toString()));

		scene = new Scene();
//        println(jsonify(scene.toString()));

        geometry = new BoxGeometry( 0.2, 0.2, 0.2 );
        println(jsonify(geometry.toString()));

        material = new MeshNormalMaterial();

        mesh = new Mesh( geometry, material );
        scene.add( mesh );

        WebGLRenderer renderer = new WebGLRenderer().antialias(true);
        renderer.setSize( window.innerWidth, window.innerHeight );
        renderer.setAnimationLoop( this::animation );
    }

    void animation( long time ) {
        mesh.rotation().x(time / 2000);
        mesh.rotation().y(time / 1000);

        renderer.render( scene, camera );
    }

    public static void main(String[] args) {
    	new hello();
    }


    public static void println(Object object) {
    	System.out.println(object);
    }

    public static String jsonify(String string) {
    	return string.replaceAll("_([a-zA-Z_$][a-zA-Z0-9_$]+)(?=:)", "$1");
    }


}
