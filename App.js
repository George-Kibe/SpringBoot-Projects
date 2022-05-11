import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Entype from "react-native-vector-icons/Entypo"
import { SafeAreaView, StatusBar } from 'react-native';
import HomeScreen from './src/screens/Home';
import Property from './src/components/Property';
import feed from './assets/data/feed';

const property0 = feed[0];
const property1 = feed[3];

const App = () =>{
  return (
    <>      
      <StatusBar style='auto' barStyle='dark-content' />
      <SafeAreaView>
        <Property property={property0}/>
        <Property property={property1}/>
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
