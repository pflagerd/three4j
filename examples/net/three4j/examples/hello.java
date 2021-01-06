package net.three4j.examples;

import net.three4j.THREE;
import static net.three4j.THREE.window;
import static net.three4j.THREE.document;
import static net.three4j.THREE.KV;

class hello {

	THREE.PerspectiveCamera camera;
	THREE.Scene scene;
	THREE.WebGLRenderer renderer;
	THREE.Geometry geometry;
    THREE.Material material;
    THREE.Mesh mesh;
    
    public hello() {
        camera = new THREE.PerspectiveCamera( 70, window.innerWidth / window.innerHeight, 0.01, 10 );
        camera.position.z = 1;

        scene = new THREE.Scene();

        geometry = new THREE.BoxGeometry( 0.2, 0.2, 0.2 );
        material = new THREE.MeshNormalMaterial();

        mesh = new THREE.Mesh( geometry, material );
        scene.add( mesh );

        THREE.WebGLRenderer renderer = new THREE.WebGLRenderer(new KV("antialias", true));
        renderer.setSize( window.innerWidth, window.innerHeight );
        renderer.setAnimationLoop( this::animation );
//        document.body.appendChild( renderer.domElement );
    }

    void animation( double time ) {
        mesh.rotation.x(time / 2000);
        mesh.rotation.y(time / 1000);

        renderer.render( scene, camera );
    }
}
