import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Entype from "react-native-vector-icons/Entypo"
import { SafeAreaView, StatusBar } from 'react-native';
import HomeScreen from './src/screens/Home';


 const App = () =>{
  return (
    <>      
      <StatusBar style='auto' barStyle='dark-content' />
      <SafeAreaView>
        <HomeScreen />
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
