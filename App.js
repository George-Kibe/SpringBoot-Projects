import React from 'react';
import MainNavigation from './src/navigation';
import {SafeAreaView, StyleSheet} from 'react-native';

function App() {
  return (
    <SafeAreaView style={styles.container}>
      <MainNavigation />
    </SafeAreaView>
  );
}

export default App;

const styles = StyleSheet.create({
  container: {
    display: 'flex',
    flex: 1,
  },
});
