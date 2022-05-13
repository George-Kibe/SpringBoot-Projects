import { View, Text, Pressable } from 'react-native'
import React, {useState} from 'react'
import styles from './styles'
import { useNavigation } from '@react-navigation/native';

const GuestsScreen = () => {
  const [adults, setAdults] = useState(0)
  const [children, setChildren] = useState(0)
  const navigation = useNavigation()

  return (
    <View style={{justifyContent:"space-between", height:"100%"}}>
      <View>
      <View style={styles.row}>
          <View>
            <Text style={{fontWeight:'bold'}}>Adults</Text>
            <Text style={{color: '#b4b4b4'}}>Age 13 and Above </Text>
          </View>
          <View style={{flexDirection:"row", alignItems:"center"}}>
            <Pressable style={styles.button}
              onPress={() => setAdults(Math.max(0,adults-1))}>
              <Text style={{fontSize:20, color:"#5d5d5d"}}>-</Text>
            </Pressable>
            <Text style={{marginHorizontal:10, fontSize:16}}>{adults}</Text>
            <Pressable style={styles.button}
              onPress={() => setAdults(adults+1)}>
              <Text style={{fontSize:20, color:"#5d5d5d"}}>+</Text>
            </Pressable>
          </View>
        </View>
        
        <View style={styles.row}>
          <View>
            <Text style={{fontWeight:'bold'}}>Children</Text>
            <Text style={{color: '#b4b4b4'}}>Age 13 and Below </Text>
          </View>
          <View style={{flexDirection:"row", alignItems:"center"}}>
            <Pressable style={styles.button}
              onPress={() => setChildren(Math.max(0,children-1))}>
              <Text style={{fontSize:20, color:"#5d5d5d"}}>-</Text>
            </Pressable>
            <Text style={{marginHorizontal:10, fontSize:16}}>{children}</Text>
            <Pressable style={styles.button}
              onPress={() => setChildren(children+1)}>
              <Text style={{fontSize:20, color:"#5d5d5d"}}>+</Text>
            </Pressable>
          </View>
        </View>
      </View>

      <Pressable onPress={() => navigation.navigate("Home",
          {screen:"Explore", 
            params:{
              screen:"Search Results"
            }}
            )}        
        style={{ marginBottom:20, backgroundColor:"#f15454",
                alignItems:"center", justifyContent:"center",
                height:50, marginHorizontal:20,
                borderRadius:15
              }}>
        <Text style={{fontSize:20, color:"white", fontWeight:"bold"}}>View Available</Text>
      </Pressable>      
    </View>
  )
};

export default GuestsScreen;