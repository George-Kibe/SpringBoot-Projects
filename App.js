import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Entypo from "react-native-vector-icons/Entypo"
import { SafeAreaView, StatusBar } from 'react-native';
import HomeScreen from './src/screens/Home';
import SearchResultsScreen from './src/screens/SearchResults';
import LocationSearchScreen from './src/screens/LocationSearch';
import GuestsScreen from './src/screens/Guests';

import Property from './src/components/Property';
import feed from './assets/data/feed';


const property0 = feed[0];
const property1 = feed[3];

const App = () =>{
  return (
    <>      
      <StatusBar style='auto'/> 
      <SafeAreaView>
        <GuestsScreen />
      </SafeAreaView>      
    </>
  );
}

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
