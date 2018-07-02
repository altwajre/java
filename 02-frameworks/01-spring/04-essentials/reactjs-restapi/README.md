# Reactjs - RestApi

pom.xml

```
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>

      <plugin>
        <groupId>com.github.eirslett</groupId>
        <artifactId>frontend-maven-plugin</artifactId>
        <version>1.2</version>
        <configuration>
          <installDirectory>target</installDirectory>
        </configuration>
        <executions>
          <execution>
            <id>install node and npm</id>
            <goals>
              <goal>install-node-and-npm</goal>
            </goals>
            <configuration>
              <nodeVersion>v4.4.5</nodeVersion>
              <npmVersion>3.9.2</npmVersion>
            </configuration>
          </execution>
          <execution>
            <id>npm install</id>
            <goals>
              <goal>npm</goal>
            </goals>
            <configuration>
              <arguments>install</arguments>
            </configuration>
          </execution>
          <execution>
            <id>webpack build</id>
            <goals>
              <goal>webpack</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

    </plugins>
  </build>
```

resources/templates/index.html

```
<script src="built/bundle.js"></script>
```

## Add React js stuff

package.json

```
{
  "name": "Reactjs-RestAPI",
  "version": "0.1.0",
  "dependencies": {
    "react": "^15.6.1",
    "react-dom": "^15.6.1",
    "rest": "^1.3.1"
  },
  "scripts": {
    "watch": "webpack --watch -d"
  },
  "description": "Demo of ReactJS + REST",
  "main": "index.js",
  "devDependencies": {
    "babel-core": "^6.26.0",
    "babel-loader": "^7.1.2",
    "babel-preset-es2015": "^6.24.1",
    "babel-preset-react": "^6.24.1",
    "babel-preset-stage-2": "^6.24.1",
    "webpack": "^3.5.5",
    "webpack-dev-server": "^2.7.1"
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
          presets: ["react", "es2015", "stage-2"]
        }
      }
    ]
  }
};
```

JavaScript files

js/*.js

## Test

mvn clean package
java -jar target/reactjs-restapi-1.0-SNAPSHOT.jar
