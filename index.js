import { registerRootComponent } from 'expo';

//import {AppRegistry} from "react-native"
import {name as AppName} from "./app.json"

import { Amplify } from 'aws-amplify'
import awsconfig from './src/aws-exports'
Amplify.configure(awsconfig)

import App from './App';

// registerRootComponent calls AppRegistry.registerComponent('main', () => App);
// It also ensures that whether you load the app in Expo Go or in a native build,
// the environment is set up appropriately
registerRootComponent(App);

//AppRegistry.registerComponent(appName, getComponenctFunc: () => App)