rm -rf node_modules dist tmp
cmd /C npm i angular-cli@latest -D
cmd /C npm install
ng init --source-dir=client --style=scss --routing=true