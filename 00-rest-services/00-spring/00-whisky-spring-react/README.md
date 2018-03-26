# Spring React

https://www.safaribooksonline.com/library/view/building-web-apps/9781788396226/video5_2.html

## Build and Run

mvn clean package
java -jar target/whisky-services-1.0-SNAPSHOT.jar

curl http://localhost:8080/whiskies
curl http://localhost:8080/whiskies/1
curl -X POST http://localhost:8080/whiskies -H 'content-type: application/json' -d '{"name": "Bowmore 18 Years", "origin": "Scotland", "fats": ["fat_11", "fat_12"]}'
curl -X POST http://localhost:8080/whiskies -H 'content-type: application/json' -d '{"name": "Bowmore 18 Years"}'
curl -X PUT http://localhost:8080/whiskies/1 -H 'content-type: application/json' -d '{"name": "Bowmore 28", "origin": "Scotland 18", "fats": ["fat_18", "fat_28"]}'
curl -X DELETE http://localhost:8080/whiskies/2

## React files

package.json

```
{
  "name": "my-app",
  "version": "0.1.0",
  "private": true,
  "dependencies": {
    "react": "^16.2.0",
    "react-dom": "^16.2.0",
    "rest": "^2.0.0",
    "react-scripts": "1.1.1"
  },
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test --env=jsdom",
    "eject": "react-scripts eject",
    "watch": "webpack --watch -d"
  },
  "devDependencies": {
    "babel-core": "^6.26.0",
    "babel-loader": "^7.1.4",
    "babel-preset-es2015": "^6.24.1",
    "babel-preset-react": "^6.24.1",
    "babel-preset-stage-2": "^6.24.1",
    "webpack": "^4.2.0",
    "webpack-dev-server": "^3.1.1"
  }
}
```

webpack.config.js

```
var path = require('path');
module.exports = {
    entry: './src/main/js/index.js',
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        loaders: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    presets: ["react","es2015","stage-2"]
                }
            }
        ]
    }
};
```
