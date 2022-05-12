import React from 'react';
import 'react-native-gesture-handler'
import { SafeAreaView, StatusBar } from 'react-native';
import LocationSearchScreen from './src/screens/LocationSearch';
import GuestsScreen from './src/screens/Guests';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import Router from "./src/navigation/Router"

const App = () =>{
  return (
    <SafeAreaProvider>      
      <StatusBar style='auto'/> 
      
        <Router />
           
    </SafeAreaProvider>
  );
}

export default App;
