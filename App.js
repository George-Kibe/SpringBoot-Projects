import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import Entype from "react-native-vector-icons"


export default function App() {
  return (
    <View style={styles.container}>
      <Text>AirBnb here we go!</Text>
      <Text>AirBnb here we go!</Text>
      {/* <Entype name={"Analytics"} /> */}
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
