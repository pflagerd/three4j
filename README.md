three4j
========

[![NPM Package][npm]][npm-url]
[![Build Size][build-size]][build-size-url]
[![NPM Downloads][npm-downloads]][npmtrends-url]
[![Dev Dependencies][dev-dependencies]][dev-dependencies-url]
[![Language Grade][lgtm]][lgtm-url]

#### Java 3D library ####

The aim of the project is to create an easy to use, lightweight, 3D library with a default OpenGL 1.1 renderer. The library also provides Canvas WebGL, GLES 2.0, 2D, SVG and CSS3D renderers in the examples.

[Examples](http://threejs.org/examples/) &mdash;
[Documentation](http://threejs.org/docs/) &mdash;
[Wiki](https://github.com/mrdoob/three.js/wiki) &mdash;
[Migrating](https://github.com/mrdoob/three.js/wiki/Migration-Guide) &mdash;
[Questions](http://stackoverflow.com/questions/tagged/three.js) &mdash;
[Forum](https://discourse.threejs.org/) &mdash;
[Slack](https://join.slack.com/t/threejs/shared_invite/enQtMzYxMzczODM2OTgxLTQ1YmY4YTQxOTFjNDAzYmQ4NjU2YzRhNzliY2RiNDEyYjU2MjhhODgyYWQ5Y2MyZTU3MWNkOGVmOGRhOTQzYTk) &mdash;
[Discord](https://discordapp.com/invite/HF4UdyF)

### Usage ###

This code creates a scene, a camera, and a geometric cube, and it adds the cube to the scene. It then creates a `WebGL` renderer for the scene and camera, and it adds that viewport to the `document.body` element. Finally, it animates the cube within the scene for the camera.

```javascript
import * as THREE from './js/three.module.js';

let camera, scene, renderer;
let geometry, material, mesh;

init();

function init() {

	camera = new THREE.PerspectiveCamera( 70, window.innerWidth / window.innerHeight, 0.01, 10 );
	camera.position.z = 1;

	scene = new THREE.Scene();

	geometry = new THREE.BoxGeometry( 0.2, 0.2, 0.2 );
	material = new THREE.MeshNormalMaterial();

	mesh = new THREE.Mesh( geometry, material );
	scene.add( mesh );

	renderer = new THREE.WebGLRenderer( { antialias: true } );
	renderer.setSize( window.innerWidth, window.innerHeight );
	renderer.setAnimationLoop( animation );
	document.body.appendChild( renderer.domElement );

}

function animation( time ) {

	mesh.rotation.x = time / 2000;
	mesh.rotation.y = time / 1000;

	renderer.render( scene, camera );

}
```

Becomes:

```java
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
        document.body.appendChild( renderer.domElement );
    }

    void animation( double time ) {
        mesh.rotation.x(time / 2000);
        mesh.rotation.y(time / 1000);

        renderer.render( scene, camera );
    }
}
```

If everything went well, you should see [this](https://jsfiddle.net/yf6nks2o/).



### Cloning this repository ###

Cloning the repo with all its history results in a ~2GB download. If you don't need the whole history you can use the `depth` parameter to significantly reduce download size.

```sh
git clone --depth=1 https://github.com/mrdoob/three.js.git
```



### Building



### What's the relationship between the threejs code base and three4j?

