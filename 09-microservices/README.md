# RabbitMQ
https://www.rabbitmq.com/install-homebrew.html
https://www.youtube.com/watch?v=8mFsh1cwlsA

## Launch RabbitMQ

```
To have launchd start rabbitmq at login:
  ln -sfv /usr/local/opt/rabbitmq/*.plist ~/Library/LaunchAgents
Then to load rabbitmq now:
  launchctl load ~/Library/LaunchAgents/homebrew.mxcl.rabbitmq.plist
Or, if you don't want/need launchctl, you can just run:
  rabbitmq-server
```

## RabbitMQ Management
http://localhost:15672
