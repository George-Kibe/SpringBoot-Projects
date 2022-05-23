import React from 'react';
import 'react-native-gesture-handler'
import { SafeAreaView, StatusBar } from 'react-native';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import Router from "./src/navigation/Router"

//Aws auth
import {withAuthenticator} from 'aws-amplify-react-native'

const App = () =>{
  return (
    <SafeAreaProvider>      
      <StatusBar style='auto'/> 
      
        <Router />
           
    </SafeAreaProvider>
  );
}

export default withAuthenticator(App);
